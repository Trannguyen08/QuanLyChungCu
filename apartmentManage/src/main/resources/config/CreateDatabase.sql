CREATE DATABASE apartment;
USE apartment;

CREATE TABLE accounts (
    id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phoneNum varchar(11) NOT NULL UNIQUE,
    role ENUM('user', 'manager', 'customer', 'employee') NOT NULL DEFAULT 'customer'
);

CREATE TABLE apartments (
    apartment_id INT PRIMARY KEY, 
    apartmentIndex INT NOT NULL,
    floor INT NOT NULL,  
    building VARCHAR(5) NOT NULL,  
    num_rooms INT NOT NULL, 
    num_wcs INT NOT NULL,
    interior ENUM('Cơ bản', 'Đầy đủ', 'Trống') NOT NULL DEFAULT 'Cơ bản',
    status ENUM('Trống', 'Đã thuê', 'Đã bán', 'Bảo trì') NOT NULL DEFAULT 'Trống',  
    area DECIMAL(6,2) NOT NULL,  
    rent_price DECIMAL(15,2) NOT NULL, 
    purchase_price DECIMAL(15,2) NOT NULL 
);

CREATE TABLE apartment_images (
    img_id INT PRIMARY KEY AUTO_INCREMENT,
    apartment_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE personal_info (
    person_id INT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    gender ENUM('Nam', 'Nữ', 'Khác') NOT NULL,
    dob DATE NOT NULL,
    phoneNum VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    id_card VARCHAR(20) UNIQUE
);

CREATE TABLE residents (
    resident_id INT PRIMARY KEY,
    apartment_id INT NOT NULL, 
    user_id INT NOT NULL,  
    person_id INT NOT NULL, 
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES accounts(id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES personal_info(person_id) ON DELETE CASCADE
);

CREATE TABLE contracts (
    contract_id INT PRIMARY KEY,
    apartment_id INT NOT NULL,
    resident_id INT NOT NULL,
    contract_type ENUM('Mua bán', 'Cho thuê') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NULL,
    contract_value DECIMAL(15,2) NOT NULL,
    contract_status ENUM('Hiệu lực', 'Hết hạn', 'Hủy bỏ') NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE services (
    service_id INT PRIMARY KEY,  
    service_name VARCHAR(255) NOT NULL,  
    service_type ENUM('Cố định', 'Tính theo sử dụng') NOT NULL, 
    relevant ENUM('Căn hộ', 'Cá nhân') NOT NULL,
    price DECIMAL(15,2) NOT NULL,  
    unit VARCHAR(50) NULL,  
    description TEXT NULL
);

CREATE TABLE bills (
    bill_id INT PRIMARY KEY,  
    resident_id INT,  
    bill_date DATE NOT NULL,  
    due_date DATE NOT NULL,  
    total_amount DECIMAL(15,2) NOT NULL,  
    status ENUM('Chưa thanh toán', 'Đã thanh toán') NOT NULL DEFAULT 'Chưa thanh toán',  
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE
);

CREATE TABLE bill_Detail_Users (
    id INT PRIMARY KEY,
    bill_id INT NOT NULL, 
    service_id INT NOT NULL,  
    quantity DECIMAL(10,2) NOT NULL DEFAULT 1,   
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE
);

CREATE TABLE bill_Detail_Managers (
    id INT PRIMARY KEY auto_increment,
    service_name VARCHAR(50) NOT NULL,
    price DECIMAL(15,2) NOT NULL,
    paidDate DATE NOT NULL
);

CREATE TABLE invoice_history (
    history_id INT PRIMARY KEY ,
    bill_id INT NOT NULL,
    paid_date DATE NOT NULL,
    note ENUM('Đúng hạn', 'Quá hạn') NOT NULL, 
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id)
);

CREATE TABLE employees (
	employee_id INT PRIMARY KEY,
    account_id INT NOT NULL,
    person_id INT NOT NULL,
    position ENUM('Bảo vệ', 'Lễ tân', 'Dọn dẹp') NOT NULL,
    shift ENUM('Ca sáng', 'Ca tối') NOT NULL,
    salary DECIMAL(15,2) NOT NULL,
    status ENUM('Làm việc', 'Nghỉ việc') NOT NULL DEFAULT 'Làm việc',
    FOREIGN KEY (person_id) REFERENCES personal_info(person_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);
CREATE TABLE attendances (
    attendance_id INT PRIMARY KEY,
    employee_id INT NOT NULL,
    attendance_date DATE NOT NULL,
    check_in_time TIME NULL,
    check_out_time TIME NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

CREATE TABLE notifications (
    notification_id INT PRIMARY KEY,
    recipant ENUM('Cư dân', 'Nhân viên') NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,  
    notification_type ENUM('Chung', 'Khẩn cấp') NOT NULL DEFAULT 'Chung',  
    sentDate DATE NOT NULL, 
    seen INT NOT NULL DEFAULT 0
);



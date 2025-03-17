USE apartment;

CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('user', 'manager', 'customer') NOT NULL DEFAULT 'user'
);

CREATE TABLE apartments (
    apartment_id INT AUTO_INCREMENT PRIMARY KEY,  
    floor INT NOT NULL,  
    building VARCHAR(50) NOT NULL,  
    status ENUM('Trống', 'Đã thuê', 'Đã bán', 'Bảo trì', 'Chờ duyệt') NOT NULL DEFAULT 'Trống',  
    area DECIMAL(6,2) NOT NULL,  
    purchase_price DECIMAL(15,2) NULL,  
    rental_price DECIMAL(15,2) NULL,  
    num_rooms INT NOT NULL 
);

CREATE TABLE residents (
    resident_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,  
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    id_card VARCHAR(20) UNIQUE NOT NULL,  
    date_of_birth DATE NOT NULL,
    gender ENUM('Nam', 'Nữ', 'Khác') NOT NULL,
    apartment_id INT NOT NULL,  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    apartment_id INT NOT NULL,
    resident_id INT NOT NULL,
    created_by INT NOT NULL,  -- Người lập hợp đồng (quản lý)
    contract_type ENUM('Mua bán', 'Thuê') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NULL,
    total_value DECIMAL(12,2) NOT NULL,
    payment_status ENUM('Chưa thanh toán', 'Đã thanh toán', 'Thanh toán từng phần') NOT NULL,
    contract_status ENUM('Hiệu lực', 'Hết hạn', 'Hủy bỏ') NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,  
    service_name VARCHAR(255) NOT NULL,  
    service_type ENUM('Cố định', 'Tính theo sử dụng') NOT NULL,  
    price DECIMAL(10,2) NOT NULL,  
    unit VARCHAR(50) NULL,  
    description TEXT NULL
);

CREATE TABLE bills (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,  
    apartment_id INT NOT NULL,  
    bill_date DATE NOT NULL,  
    due_date DATE NOT NULL,  
    total_amount DECIMAL(10,2) NOT NULL,  
    status ENUM('Chưa thanh toán', 'Đã thanh toán', 'Quá hạn') NOT NULL DEFAULT 'Chưa thanh toán',  
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE bill_Details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bill_id INT NOT NULL, 
    service_id INT NOT NULL,  
    quantity DECIMAL(10,2) NOT NULL DEFAULT 1, 
    total_price DECIMAL(10,2) NOT NULL,  
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,  
    full_name VARCHAR(255) NOT NULL,  
    phone_number VARCHAR(15) UNIQUE NOT NULL,  
    email VARCHAR(255) UNIQUE NOT NULL,  
    position ENUM('Bảo vệ', 'Lễ tân', 'Kỹ thuật', 'Dọn dẹp') NOT NULL,  
    salary DECIMAL(10,2) NOT NULL,  
    hire_date DATE NOT NULL,  
    status ENUM('Đang làm việc', 'Nghỉ việc') NOT NULL DEFAULT 'Đang làm việc' 
);

CREATE TABLE notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY, 
    recipient_id INT NULL,  
    title VARCHAR(255) NOT NULL, 
    message TEXT NOT NULL,  
    notification_type ENUM('Chung', 'Cá nhân', 'Khẩn cấp') NOT NULL DEFAULT 'Chung',  
    FOREIGN KEY (recipient_id) REFERENCES residents(resident_id) ON DELETE CASCADE  
);


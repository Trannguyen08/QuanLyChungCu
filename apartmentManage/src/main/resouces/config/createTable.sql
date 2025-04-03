CREATE DATABASE apartment;
USE apartment;

CREATE TABLE accounts (
    id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phoneNum varchar(11) NOT NULL,
    role ENUM('user', 'manager', 'customer') NOT NULL DEFAULT 'customer'
);

CREATE TABLE apartments (
    apartment_id INT PRIMARY KEY, 
    apartmentIndex INT NOT NULL,
    floor INT NOT NULL,  
    building VARCHAR(5) NOT NULL,  
    num_rooms INT NOT NULL, 
    status ENUM('Trống', 'Đã thuê', 'Đã bán', 'Bảo trì', 'Chờ duyệt') NOT NULL DEFAULT 'Trống',  
    area DECIMAL(6,2) NOT NULL,  
    rent_price DECIMAL(15,2) NULL, 
    purchase_price DECIMAL(15,2) NULL 
);

CREATE TABLE apartment_images (
	img_id INT PRIMARY KEY,
    apartment_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE residents (
    resident_id INT PRIMARY KEY,
    apartment_id INT NOT NULL, 
    user_id INT NOT NULL,  
    full_name VARCHAR(100) NOT NULL,
    gender ENUM('Nam', 'Nữ', 'Khác') NOT NULL,
    date_of_birth DATE NOT NULL,
    phoneNum varchar(11) NOT NULL,
    id_card VARCHAR(20) UNIQUE NOT NULL,  
    email VARCHAR(100) NOT NULL UNIQUE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE contracts (
    contract_id INT PRIMARY KEY,
    apartment_id INT NOT NULL,
    resident_id INT NOT NULL,
    contract_type ENUM('Mua bán', 'Cho thuê') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NULL,
    contract_value DECIMAL(12,2) NOT NULL,
    payment_status ENUM('Chưa thanh toán', 'Đã thanh toán', 'Trả góp') NOT NULL,
    contract_status ENUM('Hiệu lực', 'Hết hạn', 'Hủy bỏ') NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE services (
    service_id INT PRIMARY KEY,  
    service_name VARCHAR(255) NOT NULL,  
    service_type ENUM('Cố định', 'Tính theo sử dụng') NOT NULL,  
    price DECIMAL(10,2) NOT NULL,  
    unit VARCHAR(50) NULL,  
    description TEXT NULL
);

CREATE TABLE bills (
    bill_id INT PRIMARY KEY,  
    apartment_id INT NOT NULL,  
    bill_date DATE NOT NULL,  
    due_date DATE NOT NULL,  
    total_amount DECIMAL(10,2) NOT NULL,  
    status ENUM('Chưa thanh toán', 'Đã thanh toán', 'Quá hạn') NOT NULL DEFAULT 'Chưa thanh toán',  
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE bill_Details (
    id INT PRIMARY KEY,
    bill_id INT NOT NULL, 
    service_id INT NOT NULL,  
    quantity DECIMAL(10,2) NOT NULL DEFAULT 1, 
    total_price DECIMAL(10,2) NOT NULL,  
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,  
    full_name VARCHAR(255) NOT NULL,  
    gender ENUM('Nam', 'Nữ', 'Khác') NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,  
    email VARCHAR(255) UNIQUE NOT NULL,  
    position ENUM('Bảo vệ', 'Lễ tân', 'Kỹ thuật', 'Dọn dẹp') NOT NULL,  
    salary DECIMAL(10,2) NOT NULL,  
    hire_date DATE NOT NULL,  
    status ENUM('Đang làm việc', 'Nghỉ việc') NOT NULL DEFAULT 'Đang làm việc' 
);

CREATE TABLE notifications (
    notification_id INT PRIMARY KEY, 
    recipient_id INT NULL,  
    title VARCHAR(255) NOT NULL, 
    message TEXT NOT NULL,  
    notification_type ENUM('Chung', 'Cá nhân', 'Khẩn cấp') NOT NULL DEFAULT 'Chung',  
    FOREIGN KEY (recipient_id) REFERENCES residents(resident_id) ON DELETE CASCADE  
);

-- Thêm 4 tài khoản
INSERT INTO accounts (id, username, password, email, phoneNum, role) VALUES
(1, 'user1', 'password1', 'user1@example.com', '0987654321', 'user'),
(2, 'user2', 'password2', 'user2@example.com', '0987654322', 'user'),
(3, 'user3', 'password3', 'manager1@example.com', '0987654323', 'user'),
(4, 'user4', 'password4', 'admin1@example.com', '0987654324', 'user');

-- Thêm 4 căn hộ
INSERT INTO apartments (apartment_id, apartmentIndex, floor, building, num_rooms, status, area, rent_price, purchase_price) VALUES
(101, 1, 3, 'A', 2, 'Đã thuê', 75.50, 5000000.00, 2700000000.00),
(102, 2, 5, 'A', 3, 'Đã thuê', 85.00, 6000000.00, 3700000000.00),
(103, 3, 2, 'B', 4, 'Đã bán', 95.75, 8000000, 6000000000.00),
(104, 4, 7, 'C', 1, 'Bảo trì', 55.00, 4000000.00, 2500000000.00);

-- Thêm 3 cư dân
INSERT INTO residents (resident_id, apartment_id, user_id, full_name, gender, date_of_birth, phoneNum, id_card, email) VALUES
(201, 102, 1, 'Nguyễn Văn A', 'Nam', '1990-05-15', '0987111222', '123456789012', 'nguyenvana@example.com'),
(202, 103, 2, 'Trần Thị B', 'Nữ', '1985-07-20', '0987111333', '234567890123', 'tranthib@example.com'),
(203, 101, 3, 'Lê Văn C', 'Nam', '1992-11-30', '0987111444', '345678901234', 'levanc@example.com');

-- Thêm 3 hợp đồng
INSERT INTO contracts (contract_id, apartment_id, resident_id, contract_type, start_date, end_date, contract_value, payment_status, contract_status) VALUES
(301, 102, 201, 'Cho thuê', '2024-01-01', '2025-01-01', 6000000.00, 'Đã thanh toán', 'Hiệu lực'),
(302, 103, 202, 'Mua bán', '2023-06-01', NULL, 1500000000.00, 'Trả góp', 'Hiệu lực'),
(303, 101, 203, 'Cho thuê', '2024-03-01', '2025-03-01', 5000000.00, 'Chưa thanh toán', 'Hiệu lực');




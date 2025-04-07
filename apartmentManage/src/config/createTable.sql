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
    payment_status ENUM('Chưa thanh toán', 'Đã thanh toán') NOT NULL,
    contract_status ENUM('Hiệu lực', 'Hết hạn', 'Hủy bỏ') NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE services (
    service_id INT PRIMARY KEY,  
    service_name VARCHAR(255) NOT NULL,  
    service_type ENUM('Cố định', 'Tính theo sử dụng') NOT NULL,  
    relevant ENUM('Căn hộ', 'Cá nhân') NOT NULL,
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
    status ENUM('Làm việc', 'Nghỉ việc') NOT NULL DEFAULT 'Đang làm việc' 
);

CREATE TABLE notifications (
    notification_id INT PRIMARY KEY, 
    title VARCHAR(255) NOT NULL, 
    message TEXT NOT NULL,  
    notification_type ENUM('Chung', 'Cá nhân', 'Khẩn cấp') NOT NULL DEFAULT 'Chung',  
    sentDate DATE NOT NULL,
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

INSERT INTO employees (employee_id, full_name, gender, phone_number, email, position, salary, hire_date, status) 
VALUES
(1, 'Nguyễn Văn A', 'Nam', '0901234567', 'nguyenvana@email.com', 'Bảo vệ', 8000000.00, '2023-05-10', 'Làm việc'),
(2, 'Trần Thị B', 'Nữ', '0912345678', 'tranthib@email.com', 'Lễ tân', 10000000.00, '2022-08-15', 'Làm việc'),
(3, 'Lê Văn C', 'Nam', '0923456789', 'levanc@email.com', 'Kỹ thuật', 12000000.00, '2021-03-20', 'Làm việc'),
(4, 'Hoàng Mai D', 'Khác', '0934567890', 'hoangmaid@email.com', 'Dọn dẹp', 7500000.00, '2024-01-05', 'Làm việc');

INSERT INTO services (service_id, service_name, service_type, price, unit, description)
VALUES 
(1, 'Phí quản lý', 'Cố định', 200000.00, 'VNĐ/tháng', 'Phí quản lý chung cư hằng tháng'),
(2, 'Tiền điện', 'Tính theo sử dụng', 3500.00, 'VNĐ/kWh', 'Tính theo số kWh điện tiêu thụ'),
(3, 'Tiền nước', 'Tính theo sử dụng', 7000.00, 'VNĐ/m³', 'Tính theo số mét khối nước tiêu thụ'),
(4, 'Gửi xe máy', 'Cố định', 100000.00, 'VNĐ/tháng', 'Phí gửi xe máy theo tháng'),
(5, 'Internet', 'Cố định', 150000.00, 'VNĐ/tháng', 'Phí dịch vụ internet cố định hằng tháng');

INSERT INTO bills (bill_id, apartment_id, bill_date, due_date, total_amount, status) VALUES
(1, 101, '2025-03-01', '2025-03-10', 0, 'Chưa thanh toán'),
(2, 102, '2025-03-01', '2025-03-10', 0, 'Đã thanh toán'),
(3, 103, '2025-03-01', '2025-03-10', 0, 'Chưa thanh toán'),
(4, 101, '2025-02-01', '2025-03-01', 0, 'Đã thanh toán');

-- Hóa đơn 1 (apartment 101)
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 1, 1, 2, 100, 100 * price FROM services WHERE service_id = 2; -- Tiền điện
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 2, 1, 3, 10, 10 * price FROM services WHERE service_id = 3; -- Tiền nước
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 3, 1, 1, 1, 1 * price FROM services WHERE service_id = 1; -- Phí quản lý

-- Hóa đơn 2 (apartment 102)
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 4, 2, 2, 90, 90 * price FROM services WHERE service_id = 2;
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 5, 2, 3, 12, 12 * price FROM services WHERE service_id = 3;
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 6, 2, 4, 1, 1 * price FROM services WHERE service_id = 4;

-- Hóa đơn 3 (apartment 103)
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 7, 3, 2, 110, 110 * price FROM services WHERE service_id = 2;
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 8, 3, 3, 9, 9 * price FROM services WHERE service_id = 3;
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 9, 3, 5, 1, 1 * price FROM services WHERE service_id = 5;

-- Hóa đơn 4 (apartment 101 tháng 2)
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 10, 4, 2, 95, 95 * price FROM services WHERE service_id = 2;
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 11, 4, 3, 8, 8 * price FROM services WHERE service_id = 3;
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price)
SELECT 12, 4, 1, 1, 1 * price FROM services WHERE service_id = 1;

UPDATE bills SET total_amount = (
    SELECT SUM(total_price) FROM bill_Details WHERE bill_id = 1
) WHERE bill_id = 1;

UPDATE bills SET total_amount = (
    SELECT SUM(total_price) FROM bill_Details WHERE bill_id = 2
) WHERE bill_id = 2;

UPDATE bills SET total_amount = (
    SELECT SUM(total_price) FROM bill_Details WHERE bill_id = 3
) WHERE bill_id = 3;

UPDATE bills SET total_amount = (
    SELECT SUM(total_price) FROM bill_Details WHERE bill_id = 4
) WHERE bill_id = 4;




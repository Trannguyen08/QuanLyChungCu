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
    num_wcs INT NOT NULL,
    interior ENUM('Cơ bản', 'Đầy đủ', 'Trống') NOT NULL DEFAULT 'Cơ bản',
    status ENUM('Trống', 'Đã thuê', 'Đã bán', 'Bảo trì', 'Chờ duyệt') NOT NULL DEFAULT 'Trống',  
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
    relevant ENUM('Căn hộ', 'Tiện ích') NOT NULL,
    price DECIMAL(15,2) NOT NULL,  
    unit VARCHAR(50) NULL,  
    description TEXT NULL
);

CREATE TABLE bills (
    bill_id INT PRIMARY KEY,  
    apartment_id INT NOT NULL,  
    bill_date DATE NOT NULL,  
    due_date DATE NOT NULL,  
    total_amount DECIMAL(15,2) NOT NULL,  
    status ENUM('Chưa thanh toán', 'Đã thanh toán', 'Quá hạn') NOT NULL DEFAULT 'Chưa thanh toán',  
    FOREIGN KEY (apartment_id) REFERENCES apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE bill_Details (
    id INT PRIMARY KEY,
    bill_id INT NOT NULL, 
    service_id INT NOT NULL,  
    quantity DECIMAL(15,2) NOT NULL DEFAULT 1, 
    total_price DECIMAL(15,2) NOT NULL,  
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE
);

CREATE TABLE employees (
	employee_id INT PRIMARY KEY,
    person_id INT NOT NULL, -- mới
    position ENUM('Bảo vệ', 'Lễ tân', 'Kỹ thuật', 'Dọn dẹp') NOT NULL,
    salary DECIMAL(15,2) NOT NULL,
    status ENUM('Làm việc', 'Nghỉ việc') NOT NULL DEFAULT 'Làm việc',
    FOREIGN KEY (person_id) REFERENCES personal_info(person_id) ON DELETE CASCADE
);

CREATE TABLE notifications (
    notification_id INT PRIMARY KEY, 
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,  
    notification_type ENUM('Chung', 'Khẩn cấp') NOT NULL DEFAULT 'Chung',  
    sentDate DATE NOT NULL, 
    seen INT NOT NULL DEFAULT 0
);

CREATE TABLE attendances (
    attendance_id INT PRIMARY KEY,
    employee_id INT NOT NULL,
    attendance_date DATE NOT NULL,
    check_in_time TIME NULL,
    check_out_time TIME NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

INSERT INTO accounts (id, username, password, email, phoneNum, role) VALUES
(1, 'manager01', 'pass123', 'manager01@example.com', '0909123456', 'manager'),
(2, 'user01', 'pass456', 'user01@example.com', '0911123456', 'customer'),
(3, 'user02', 'pass789', 'user02@example.com', '0922123456', 'customer');

-- Insert dữ liệu mẫu vào apartments
INSERT INTO apartments (apartment_id, apartmentIndex, floor, building, num_rooms, num_wcs, interior, status, area, rent_price, purchase_price) VALUES
(1, 101, 1, 'A1', 2, 2, 'Đầy đủ', 'Trống', 70.50, 10000000.00, 1500000000.00),
(2, 102, 1, 'A1', 3, 2, 'Cơ bản', 'Đã thuê', 85.00, 12000000.00, 1800000000.00),
(3, 201, 2, 'B1', 1, 1, 'Trống', 'Bảo trì', 50.00, 8000000.00, 1200000000.00);

-- Insert dữ liệu mẫu vào apartment_images
INSERT INTO apartment_images (apartment_id, image_url) VALUES
(1, 'https://example.com/images/apt1_img1.jpg'),
(1, 'https://example.com/images/apt1_img2.jpg'),
(2, 'https://example.com/images/apt2_img1.jpg');

-- Insert dữ liệu mẫu vào personal_info
INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email, id_card) VALUES
(1, 'Nguyễn Văn A', 'Nam', '1990-01-01', '0939123456', 'vana@example.com', '123456789'),
(2, 'Trần Thị B', 'Nữ', '1995-05-15', '0949123456', 'thib@example.com', '987654321'),
(3, 'Phạm Văn C', 'Nam', '1988-08-08', '0959123456', 'vanc@example.com', '192837465');

-- Insert dữ liệu mẫu vào residents
INSERT INTO residents (resident_id, apartment_id, user_id, person_id) VALUES
(1, 2, 2, 1),
(2, 3, 3, 2);

-- Insert dữ liệu mẫu vào contracts
INSERT INTO contracts (contract_id, apartment_id, resident_id, contract_type, start_date, end_date, contract_value, contract_status) VALUES
(1, 2, 1, 'Cho thuê', '2025-01-01', '2025-12-31', 12000000.00, 'Hiệu lực'),
(2, 3, 2, 'Mua bán', '2025-02-01', NULL, 1800000000.00, 'Hiệu lực');

-- Insert dữ liệu mẫu vào services
INSERT INTO services (service_id, service_name, service_type, relevant, price, unit, description) VALUES
(1, 'Phí quản lý', 'Cố định', 'Căn hộ', 500000.00, 'Tháng', 'Phí quản lý căn hộ hàng tháng'),
(2, 'Điện sinh hoạt', 'Tính theo sử dụng', 'Căn hộ', 3500.00, 'kWh', 'Tiền điện theo đồng hồ đo'),
(3, 'Nước sinh hoạt', 'Tính theo sử dụng', 'Căn hộ', 15000.00, 'm3', 'Tiền nước theo sử dụng');

-- Insert dữ liệu mẫu vào bills
INSERT INTO bills (bill_id, apartment_id, bill_date, due_date, total_amount, status) VALUES
(1, 2, '2025-04-01', '2025-04-10', 1500000.00, 'Chưa thanh toán'),
(2, 3, '2025-04-01', '2025-04-10', 1800000.00, 'Đã thanh toán');

-- Insert dữ liệu mẫu vào bill_Details
INSERT INTO bill_Details (id, bill_id, service_id, quantity, total_price) VALUES
(1, 1, 1, 1, 500000.00),
(2, 1, 2, 200, 700000.00),
(3, 1, 3, 20, 300000.00),
(4, 2, 1, 1, 500000.00),
(5, 2, 2, 250, 875000.00),
(6, 2, 3, 25, 375000.00);

-- Insert dữ liệu mẫu vào employees
INSERT INTO employees (employee_id, person_id, position, salary, status) VALUES
(1, 3, 'Bảo vệ', 7000000.00, 'Làm việc');

-- Insert dữ liệu mẫu vào notifications
INSERT INTO notifications (notification_id, title, message, notification_type, sentDate, seen) VALUES
(1, 'Thông báo họp cư dân', 'Mời quý cư dân tham dự cuộc họp chung vào ngày 10/05.', 'Chung', '2025-04-25', 0),
(2, 'Khẩn cấp: Cắt điện', 'Cảnh báo: sẽ có cắt điện toàn bộ tòa nhà A1 từ 8h-12h ngày 29/04.', 'Khẩn cấp', '2025-04-26', 0);

-- Insert dữ liệu mẫu vào attendances
INSERT INTO attendances (attendance_id, employee_id, attendance_date, check_in_time, check_out_time) VALUES
(1, 1, '2025-04-27', '08:00:00', '17:00:00'),
(2, 1, '2025-04-28', '08:10:00', '17:05:00');



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


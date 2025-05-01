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
    resident_id INT NOT NULL,  
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
    quantity DECIMAL(15,2) NOT NULL DEFAULT 1,   
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(service_id) ON DELETE CASCADE
);

CREATE TABLE bill_Detail_Managers (
    id INT PRIMARY KEY,
    bill_id INT NOT NULL, 
    service_name VARCHAR(50) NOT NULL,
    price DECIMAL(15,2) NOT NULL,
    quantity DECIMAL(15,2) NOT NULL DEFAULT 1,   
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id) ON DELETE CASCADE
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
    person_id INT NOT NULL, -- mới
    position ENUM('Bảo vệ', 'Lễ tân', 'Dọn dẹp') NOT NULL,
    shift ENUM('Ca sáng', 'Ca tối') NOT NULL,
    salary DECIMAL(15,2) NOT NULL,
    status ENUM('Làm việc', 'Nghỉ việc') NOT NULL DEFAULT 'Làm việc',
    FOREIGN KEY (person_id) REFERENCES personal_info(person_id) ON DELETE CASCADE
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

INSERT INTO notifications (notification_id, recipant, title, message, notification_type, sentDate, seen) VALUES
(1, 'Cư dân', 'Cắt điện định kỳ', 'Thông báo cắt điện từ 8h đến 12h ngày 5/5 do bảo trì hệ thống điện.', 'Chung', '2025-05-01', 0),
(2, 'Cư dân', 'Diễn tập phòng cháy chữa cháy', 'Cư dân vui lòng tham gia diễn tập PCCC lúc 15h ngày 7/5 tại sảnh A.', 'Chung', '2025-05-01', 0),
(3, 'Cư dân', 'Sự cố mất nước khẩn cấp', 'Do sự cố ống nước vỡ, khu B sẽ mất nước từ 1h đến 6h sáng ngày 2/5.', 'Khẩn cấp', '2025-05-01', 0),
(4, 'Cư dân', 'Phun thuốc diệt muỗi', 'Ban quản lý sẽ phun thuốc diệt muỗi toàn khu lúc 6h sáng ngày 3/5, đề nghị đóng cửa sổ.', 'Chung', '2025-05-01', 0);


-- 1. Tạo tài khoản
INSERT INTO accounts (id, username, password, email, phoneNum, role)
VALUES (1, 'hoang123', 'hashed_password_here', 'hoang@gmail.com', '0901234567', 'user');

-- 2. Thêm thông tin cá nhân
INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email, id_card)
VALUES (1, 'Nguyen Van Hoang', 'Nam', '1995-08-10', '0901234567', 'hoang@gmail.com', '123456789012');

-- 3. Tạo căn hộ
INSERT INTO apartments (
    apartment_id, apartmentIndex, floor, building, 
    num_rooms, num_wcs, interior, status, area, 
    rent_price, purchase_price
) VALUES (
    101, 5, 2, 'A', 
    2, 1, 'Đầy đủ', 'Đã thuê', 75.50, 
    12000000.00, 1500000000.00
);

-- 4. Gán người dùng vào căn hộ
INSERT INTO residents (resident_id, apartment_id, user_id, person_id)
VALUES (1, 101, 1, 1);

-- 5. Tạo hợp đồng thuê căn hộ
INSERT INTO contracts (
    contract_id, apartment_id, resident_id, contract_type, 
    start_date, end_date, contract_value, contract_status
) VALUES (
    1, 101, 1, 'Cho thuê', 
    '2024-01-01', '2025-01-01', 12000000.00, 'Hiệu lực'
);


INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email, id_card)
VALUES (1, 'Nguyễn Văn A', 'Nam', '1995-03-15', '0912345678', 'nguyenvana@gmail.com', '012345678');
INSERT INTO employees (employee_id, person_id, position, shift, salary, status)
VALUES (1001, 1, 'Bảo vệ', 'Ca sáng', 7000000, 'Làm việc');
INSERT INTO attendances (attendance_id, employee_id, attendance_date, check_in_time, check_out_time) VALUES
(1, 1001, '2025-04-01', '08:00:00', '17:00:00'),
(2, 1001, '2025-04-02', '08:05:00', '17:10:00'),
(3, 1001, '2025-04-03', '07:55:00', '17:05:00'),
(4, 1001, '2025-04-04', '08:10:00', '17:00:00'),
(5, 1001, '2025-04-05', '08:03:00', '17:08:00'),
(6, 1001, '2025-04-06', '08:00:00', '17:00:00'),
(7, 1001, '2025-04-07', '07:58:00', '17:12:00'),
(8, 1001, '2025-04-08', '08:07:00', '17:05:00'),
(9, 1001, '2025-04-09', '08:01:00', '17:00:00'),
(10, 1001, '2025-04-10', '08:04:00', '17:06:00'),
(11, 1001, '2025-04-11', '08:00:00', '17:00:00'),
(12, 1001, '2025-04-12', '07:59:00', '17:01:00'),
(13, 1001, '2025-04-13', '08:02:00', '17:10:00'),
(14, 1001, '2025-04-14', '08:08:00', '17:00:00'),
(15, 1001, '2025-04-15', '08:00:00', '17:04:00'),
(16, 1001, '2025-04-16', '08:06:00', '17:03:00'),
(17, 1001, '2025-04-17', '08:00:00', '17:00:00'),
(18, 1001, '2025-04-18', '08:09:00', '17:15:00'),
(19, 1001, '2025-04-19', '07:57:00', '17:00:00'),
(20, 1001, '2025-04-20', '08:00:00', '17:05:00'),
(21, 1001, '2025-04-21', '08:03:00', '17:07:00'),
(22, 1001, '2025-04-22', '08:00:00', '17:00:00'),
(23, 1001, '2025-04-23', '08:01:00', '17:02:00'),
(24, 1001, '2025-04-24', '07:56:00', '17:05:00'),
(25, 1001, '2025-04-25', '08:10:00', '17:00:00'),
(26, 1001, '2025-04-26', '08:00:00', '17:00:00');

-- Thêm các hóa đơn mới
INSERT INTO bills (bill_id, apartment_id, bill_date, due_date, total_amount, status) VALUES
(21, 1, '2025-04-01', '2025-04-30', 800000.00, 'Chưa thanh toán'),
(22, 2, '2025-04-01', '2025-04-30', 1200000.00, 'Chưa thanh toán'),
(23, 3, '2025-04-01', '2025-04-30', 1000000.00, 'Chưa thanh toán'),
(24, 4, '2025-04-01', '2025-04-30', 950000.00, 'Chưa thanh toán'),
(25, 5, '2025-04-01', '2025-04-30', 850000.00, 'Chưa thanh toán'),
(26, 6, '2025-04-01', '2025-04-30', 1100000.00, 'Chưa thanh toán'),
(27, 7, '2025-04-01', '2025-04-30', 1300000.00, 'Chưa thanh toán'),
(28, 8, '2025-04-01', '2025-04-30', 1000000.00, 'Chưa thanh toán'),
(29, 9, '2025-04-01', '2025-04-30', 900000.00, 'Chưa thanh toán'),
(30, 10, '2025-04-01', '2025-04-30', 950000.00, 'Chưa thanh toán'),
(31, 11, '2025-04-01', '2025-04-30', 880000.00, 'Chưa thanh toán'),
(32, 12, '2025-04-01', '2025-04-30', 1050000.00, 'Chưa thanh toán'),
(33, 13, '2025-04-01', '2025-04-30', 1150000.00, 'Chưa thanh toán'),
(34, 14, '2025-04-01', '2025-04-30', 1050000.00, 'Chưa thanh toán'),
(35, 15, '2025-04-01', '2025-04-30', 980000.00, 'Chưa thanh toán'),
(36, 16, '2025-04-01', '2025-04-30', 1200000.00, 'Chưa thanh toán'),
(37, 17, '2025-04-01', '2025-04-30', 1070000.00, 'Chưa thanh toán'),
(38, 18, '2025-04-01', '2025-04-30', 980000.00, 'Chưa thanh toán'),
(39, 19, '2025-04-01', '2025-04-30', 1100000.00, 'Chưa thanh toán'),
(40, 20, '2025-04-01', '2025-04-30', 950000.00, 'Chưa thanh toán');

-- Thêm các chi tiết hóa đơn của người dùng
INSERT INTO bill_Detail_Users (id, bill_id, service_id, quantity) VALUES
(79, 21, 1, 5.5),
(80, 21, 2, 10.2),
(81, 21, 3, 8.1),
(82, 21, 4, 2.4),
(83, 22, 1, 6.3),
(84, 22, 2, 9.6),
(85, 22, 3, 7.9),
(86, 22, 4, 4.2),
(87, 23, 1, 4.8),
(88, 23, 2, 6.5),
(89, 23, 3, 9.3),
(90, 23, 5, 2.3),
(91, 24, 1, 7.2),
(92, 24, 2, 3.1),
(93, 24, 3, 8.4),
(94, 24, 5, 1.5),
(95, 25, 1, 3.8),
(96, 25, 2, 4.3),
(97, 25, 3, 6.7),
(98, 25, 4, 2.9);

-- Thêm các chi tiết hóa đơn của người quản lý
INSERT INTO bill_Detail_Managers (id, bill_id, service_name, price, quantity) VALUES
(60, 21, 'Bảo trì thang máy', 100000, 1),
(61, 21, 'Phòng cháy chữa cháy', 200000, 1),
(62, 22, 'Bảo trì thang máy', 100000, 1),
(63, 22, 'Phòng cháy chữa cháy', 200000, 1),
(64, 23, 'Bảo trì thang máy', 100000, 1),
(65, 23, 'Phòng cháy chữa cháy', 200000, 1),
(66, 24, 'Bảo trì thang máy', 100000, 1),
(67, 24, 'Phòng cháy chữa cháy', 200000, 1),
(68, 25, 'Bảo trì thang máy', 100000, 1),
(69, 25, 'Phòng cháy chữa cháy', 200000, 1),
(70, 26, 'Bảo trì thang máy', 100000, 1),
(71, 26, 'Phòng cháy chữa cháy', 200000, 1),
(72, 27, 'Bảo trì thang máy', 100000, 1),
(73, 27, 'Phòng cháy chữa cháy', 200000, 1),
(74, 28, 'Bảo trì thang máy', 100000, 1),
(75, 28, 'Phòng cháy chữa cháy', 200000, 1),
(76, 29, 'Bảo trì thang máy', 100000, 1),
(77, 29, 'Phòng cháy chữa cháy', 200000, 1),
(78, 30, 'Bảo trì thang máy', 100000, 1),
(79, 30, 'Phòng cháy chữa cháy', 200000, 1);





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
(3, 'Nước sinh hoạt', 'Tính theo sử dụng', 'Căn hộ', 15000.00, 'm3', 'Tiền nước theo sử dụng'),
(4, 'Internet', 'Tính theo sử dụng', 'Căn hộ', 200000.00, 'Tháng', 'Phí internet cho cư dân chung cư'),
(5, 'Phí gửi xe', 'Tính theo sử dụng', 'Căn hộ', 150000.00, 'Tháng', 'Phí gửi xe máy hoặc ô tô');



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


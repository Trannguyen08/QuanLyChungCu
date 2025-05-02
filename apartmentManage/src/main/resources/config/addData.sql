use apartment;
-- Insert 5 additional accounts with role 'user'
INSERT INTO accounts (id, username, password, email, phoneNum, role) VALUES
(1, 'admin', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'user00@example.com', '0912345688', 'manager'),
(3, 'user01', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'user01@example.com', '0912345678', 'user'),
(4, 'user02', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'user02@example.com', '0912345679', 'user'),
(5, 'user03', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'user03@example.com', '0912345680', 'user'),
(6, 'user04', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'user04@example.com', '0912345681', 'user'),
(7, 'user05', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'user05@example.com', '0912345682', 'user');

-- Insert 5 additional personal_info records corresponding to the new accounts
INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email, id_card) VALUES
(3, 'Trần Thị B', 'Nữ', '1996-03-20', '0912345678', 'user01@example.com', '123456790'),
(4, 'Lê Văn C', 'Nam', '1997-07-12', '0912345679', 'user02@example.com', '123456791'),
(5, 'Phạm Thị D', 'Nữ', '1998-11-25', '0912345680', 'user03@example.com', '123456792'),
(6, 'Hoàng Văn E', 'Nam', '1994-02-14', '0912345681', 'user04@example.com', '123456793'),
(7, 'Nguyễn Thị F', 'Nữ', '1993-09-30', '0912345682', 'user05@example.com', '123456794');

-- Insert 5 additional apartments
INSERT INTO apartments (
    apartment_id, apartmentIndex, floor, building, 
    num_rooms, num_wcs, interior, status, area, 
    rent_price, purchase_price
) VALUES
(102, 6, 3, 'A', 3, 2, 'Đầy đủ', 'Đã thuê', 85.00, 15000000.00, 1800000000.00),
(103, 7, 4, 'B', 2, 1, 'Cơ bản', 'Đã thuê', 70.00, 10000000.00, 1400000000.00),
(104, 8, 5, 'A', 4, 2, 'Đầy đủ', 'Đã thuê', 100.00, 20000000.00, 2500000000.00),
(105, 9, 6, 'B', 2, 1, 'Cơ bản', 'Đã thuê', 65.00, 9000000.00, 1300000000.00),
(106, 10, 7, 'A', 3, 2, 'Đầy đủ', 'Đã thuê', 90.00, 17000000.00, 2000000000.00);

INSERT INTO apartment_images (apartment_id, image_url) VALUES
(102, 'C:\Users\Nguyen\Downloads\cc1.png'),
(103, 'C:\Users\Nguyen\Downloads\cc2.png'),
(104, 'C:\Users\Nguyen\Downloads\cc3.png'),
(105, 'C:\Users\Nguyen\Downloads\cc4.png'),
(106, 'C:\Users\Nguyen\Downloads\cc5.png');



-- Insert 5 additional residents
INSERT INTO residents (resident_id, apartment_id, user_id, person_id) VALUES
(2, 102, 3, 3),
(3, 103, 4, 4),
(4, 104, 5, 5),
(5, 105, 6, 6),
(6, 106, 7, 7);

-- Insert 5 additional contracts
INSERT INTO contracts (
    contract_id, apartment_id, resident_id, contract_type, 
    start_date, end_date, contract_value, contract_status
) VALUES
(2, 102, 2, 'Cho thuê', '2025-02-01', '2025-07-01', 15000000.00, 'Hiệu lực'),
(3, 103, 3, 'Cho thuê', '2025-02-01', '2025-09-01', 10000000.00, 'Hiệu lực'),
(4, 104, 4, 'Cho thuê', '2025-02-01', '2025-08-01', 20000000.00, 'Hiệu lực'),
(5, 105, 5, 'Cho thuê', '2025-02-01', '2025-09-01', 9000000.00, 'Hiệu lực'),
(6, 106, 6, 'Cho thuê', '2024-03-01', '2025-09-01', 17000000.00, 'Hiệu lực');

-- Insert 10 additional bills
INSERT INTO bills (bill_id, resident_id, bill_date, due_date, total_amount, status) VALUES
(2, 2, '2025-03-01', '2025-03-10', 0, 'Đã thanh toán'),
(3, 2, '2025-05-01', '2025-05-10', 0, 'Chưa thanh toán'),
(4, 3, '2025-03-01', '2025-03-10', 0, 'Đã thanh toán'),
(5, 3, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(6, 4, '2025-03-01', '2025-03-10', 0, 'Đã thanh toán'),
(7, 4, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(8, 5, '2025-03-01', '2025-03-10', 0, 'Đã thanh toán'),
(9, 5, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(10, 6, '2025-04-01', '2025-04-10', 0, 'Đã thanh toán'),
(11, 6, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(22, 2, '2025-04-01', '2025-04-10', 0, 'Đã thanh toán'),
(23, 3, '2025-04-01', '2025-04-10', 0, 'Đã thanh toán'),
(24, 4, '2025-04-01', '2025-04-10', 0, 'Đã thanh toán'),
(25, 5, '2025-04-01', '2025-04-10', 0, 'Đã thanh toán');
INSERT INTO bills (bill_id, bill_date, due_date, total_amount, status) VALUES
(12, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(13, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(14, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(15, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(16, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(17, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(18, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(19, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(20, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán'),
(21, '2025-05-01', '2025-05-10', 0, 'Đã thanh toán');

INSERT INTO services (service_id, service_name, service_type, relevant, price, unit, description) VALUES
(1, 'Phí quản lý', 'Cố định', 'Căn hộ', 500000.00, 'Tháng', 'Phí quản lý căn hộ hàng tháng'),
(2, 'Điện sinh hoạt', 'Tính theo sử dụng', 'Căn hộ', 3500.00, 'kWh', 'Tiền điện theo đồng hồ đo'),
(3, 'Nước sinh hoạt', 'Tính theo sử dụng', 'Căn hộ', 15000.00, 'm3', 'Tiền nước theo sử dụng'),
(4, 'Internet', 'Tính theo sử dụng', 'Căn hộ', 200000.00, 'Tháng', 'Phí internet cho cư dân chung cư'),
(5, 'Phí gửi xe', 'Tính theo sử dụng', 'Căn hộ', 150000.00, 'Tháng', 'Phí gửi xe máy hoặc ô tô');

-- Insert bill details for users
INSERT INTO bill_Detail_Users (id, bill_id, service_id, quantity) VALUES
(4, 2, 1, 1),    -- Phí quản lý: 500,000 * 1
(5, 2, 2, 100),  -- Điện sinh hoạt: 3,500 * 100 kWh
(6, 2, 3, 11),   -- Nước sinh hoạt: 15,000 * 11 m³
(7, 3, 1, 1),    -- Phí quản lý: 500,000 * 1
(8, 3, 2, 80),   -- Điện sinh hoạt: 3,500 * 80 kWh
(9, 4, 1, 1),    -- Phí quản lý: 500,000 * 1
(10, 4, 3, 10),  -- Nước sinh hoạt: 15,000 * 10 m³
(11, 5, 1, 1),   -- Phí quản lý: 500,000 * 1
(12, 5, 2, 110), -- Điện sinh hoạt: 3,500 * 110 kWh
(13, 6, 1, 1),   -- Phí quản lý: 500,000 * 1
(14, 6, 3, 12),  -- Nước sinh hoạt: 15,000 * 12 m³
(15, 7, 1, 1),   -- Phí quản lý: 500,000 * 1
(16, 7, 2, 70),  -- Điện sinh hoạt: 3,500 * 70 kWh
(17, 8, 1, 1),   -- Phí quản lý: 500,000 * 1
(18, 8, 3, 13),  -- Nước sinh hoạt: 15,000 * 13 m³
(19, 9, 1, 1),   -- Phí quản lý: 500,000 * 1
(20, 9, 2, 90),  -- Điện sinh hoạt: 3,500 * 90 kWh
(21, 10, 1, 1),  -- Phí quản lý: 500,000 * 1
(22, 10, 3, 11), -- Nước sinh hoạt: 15,000 * 11 m³
(23, 22, 1, 1),  -- Phí quản lý: 500,000 * 1
(24, 22, 2, 100),-- Điện sinh hoạt: 3,500 * 100 kWh
(25, 23, 1, 1),  -- Phí quản lý: 500,000 * 1
(26, 23, 2, 100),-- Điện sinh hoạt: 3,500 * 100 kWh
(27, 24, 1, 1),  -- Phí quản lý: 500,000 * 1
(28, 24, 2, 100),-- Điện sinh hoạt: 3,500 * 100 kWh
(29, 25, 1, 1),  -- Phí quản lý: 500,000 * 1
(30, 25, 2, 100);-- Điện sinh hoạt: 3,500 * 100 kWh

-- Insert bill details for managers
INSERT INTO bill_Detail_Managers (id, bill_id, service_name, price, quantity) VALUES
(80, 12, 'Bảo trì thang máy', 100000, 1),
(81, 12, 'Phòng cháy chữa cháy', 200000, 1),
(82, 13, 'Bảo trì thang máy', 100000, 1),
(83, 13, 'Phòng cháy chữa cháy', 200000, 1),
(84, 14, 'Bảo trì thang máy', 100000, 1),
(85, 14, 'Phòng cháy chữa cháy', 200000, 1),
(86, 15, 'Bảo trì thang máy', 100000, 1),
(87, 15, 'Phòng cháy chữa cháy', 200000, 1),
(88, 16, 'Bảo trì thang máy', 100000, 1),
(89, 16, 'Phòng cháy chữa cháy', 200000, 1),
(90, 17, 'Bảo trì thang máy', 100000, 1),
(91, 17, 'Phòng cháy chữa cháy', 200000, 1),
(92, 18, 'Bảo trì thang máy', 100000, 1),
(93, 18, 'Phòng cháy chữa cháy', 200000, 1),
(94, 19, 'Bảo trì thang máy', 100000, 1),
(95, 19, 'Phòng cháy chữa cháy', 200000, 1),
(96, 20, 'Bảo trì thang máy', 100000, 1),
(97, 20, 'Phòng cháy chữa cháy', 200000, 1),
(98, 21, 'Bảo trì thang máy', 100000, 1),
(99, 21, 'Phòng cháy chữa cháy', 200000, 1);

-- Insert invoice history for paid bills
INSERT INTO invoice_history (history_id, bill_id, paid_date, note) VALUES
(1,  2,  '2025-03-05', 'Đúng hạn'),
(2,  4,  '2025-03-09', 'Đúng hạn'),
(3,  5,  '2025-05-12', 'Quá hạn'),
(4,  6,  '2025-03-08', 'Đúng hạn'),
(5,  7,  '2025-05-11', 'Quá hạn'),
(6,  8,  '2025-03-10', 'Đúng hạn'),
(7,  9,  '2025-05-10', 'Đúng hạn'),
(8,  10, '2025-04-09', 'Đúng hạn'),
(9,  11, '2025-05-10', 'Đúng hạn'),
(10, 22, '2025-04-08', 'Đúng hạn'),
(11, 23, '2025-04-15', 'Quá hạn'),
(12, 24, '2025-04-10', 'Đúng hạn'),
(13, 25, '2025-04-12', 'Quá hạn');


-- Insert 5 additional accounts with role 'employee'
INSERT INTO accounts (id, username, password, email, phoneNum, role) VALUES
(8, 'employee02', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'emp02@example.com', '0912345683', 'employee'),
(9, 'employee03', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'emp03@example.com', '0912345684', 'employee'),
(10, 'employee04', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'emp04@example.com', '0912345685', 'employee'),
(11, 'employee05', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'emp05@example.com', '0912345686', 'employee'),
(12, 'employee06', '$2a$12$.d.prbGywaxlKl8eGcGPgeHOzd2lpV0Vxu1HNPyJzYcu1KS7diw2q', 'emp06@example.com', '0912345687', 'employee');

-- Insert 5 additional personal_info records for employees
INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email, id_card) VALUES
(8, 'Trần Văn G', 'Nam', '1990-01-10', '0912345683', 'emp02@example.com', '123456795'),
(9, 'Lê Thị H', 'Nữ', '1992-04-22', '0912345684', 'emp03@example.com', '123456796'),
(10, 'Phạm Văn I', 'Nam', '1988-08-15', '0912345685', 'emp04@example.com', '123456797'),
(11, 'Hoàng Thị J', 'Nữ', '1991-12-05', '0912345686', 'emp05@example.com', '123456798'),
(12, 'Nguyễn Văn K', 'Nam', '1989-06-18', '0912345687', 'emp06@example.com', '123456799');

-- Insert 5 additional employees
INSERT INTO employees (employee_id, account_id, person_id, position, shift, salary, status) VALUES
(2, 8, 8, 'Bảo vệ', 'Ca tối', 7500000, 'Làm việc'),
(3, 9, 9, 'Bảo vệ', 'Ca sáng', 8000000, 'Làm việc'),
(4, 10, 10, 'Lễ tân', 'Ca tối', 7000000, 'Làm việc'),
(5, 11, 11, 'Dọn dẹp', 'Ca sáng', 6500000, 'Làm việc'),
(6, 12, 12, 'Bảo vệ', 'Ca tối', 7500000, 'Làm việc');

-- Insert 20 additional attendance records for employees
INSERT INTO attendances (attendance_id, employee_id, attendance_date, check_in_time, check_out_time) VALUES
(7, 2, '2025-04-01', '18:00:00', '02:00:00'),
(8, 2, '2025-04-02', '18:02:00', '02:05:00'),
(9, 2, '2025-04-03', '18:01:00', '02:03:00'),
(10, 2, '2025-04-04', '18:00:00', '02:00:00'),
(11, 3, '2025-04-01', '08:00:00', '17:00:00'),
(12, 3, '2025-04-02', '08:03:00', '17:02:00'),
(13, 3, '2025-04-03', '08:01:00', '17:04:00'),
(14, 3, '2025-04-04', '08:00:00', '17:00:00'),
(15, 4, '2025-04-01', '14:00:00', '22:00:00'),
(16, 4, '2025-04-02', '14:02:00', '22:01:00'),
(17, 4, '2025-04-03', '14:00:00', '22:00:00'),
(18, 4, '2025-04-04', '14:01:00', '22:03:00'),
(19, 5, '2025-04-01', '08:00:00', '17:00:00'),
(20, 5, '2025-04-02', '08:02:00', '17:05:00'),
(21, 5, '2025-04-03', '08:01:00', '17:03:00'),
(22, 5, '2025-04-04', '08:00:00', '17:00:00'),
(23, 6, '2025-04-01', '18:00:00', '03:00:00'),
(24, 6, '2025-04-02', '18:03:00', '03:02:00'),
(25, 6, '2025-04-03', '18:01:00', '03:04:00'),
(26, 6, '2025-04-04', '18:00:00', '03:00:00');

INSERT INTO notifications (notification_id, recipant, title, message, notification_type, sentDate, seen) VALUES
(1, 'Nhân viên', 'Cắt điện định kỳ', 'Thông báo cắt điện từ 8h đến 12h ngày 5/5 do bảo trì hệ thống điện.', 'Chung', '2025-05-01', 0),
(2, 'Nhân viên', 'Diễn tập phòng cháy chữa cháy', 'Cư dân vui lòng tham gia diễn tập PCCC lúc 15h ngày 7/5 tại sảnh A.', 'Chung', '2025-05-01', 0),
(3, 'Nhân viên', 'Sự cố mất nước khẩn cấp', 'Do sự cố ống nước vỡ, khu B sẽ mất nước từ 1h đến 6h sáng ngày 2/5.', 'Khẩn cấp', '2025-05-01', 0),
(4, 'Nhân viên', 'Phun thuốc diệt muỗi', 'Ban quản lý sẽ phun thuốc diệt muỗi toàn khu lúc 6h sáng ngày 3/5, đề nghị đóng cửa sổ.', 'Chung', '2025-05-01', 0);
INSERT INTO notifications (notification_id, recipant, title, message, notification_type, sentDate, seen) VALUES
(5, 'Cư dân', 'Sự cố mất nước khẩn cấp', 'Do sự cố ống nước vỡ, khu B sẽ mất nước từ 1h đến 6h sáng ngày 2/5.', 'Khẩn cấp', '2025-05-01', 0),
(6, 'Cư dân', 'Phun thuốc diệt muỗi', 'Ban quản lý sẽ phun thuốc diệt muỗi toàn khu lúc 6h sáng ngày 3/5, đề nghị đóng cửa sổ.', 'Chung', '2025-05-01', 0),
(7, 'Cư dân', 'Sự cố mất nước khẩn cấp', 'Do sự cố ống nước vỡ, khu B sẽ mất nước từ 1h đến 6h sáng ngày 2/5.', 'Khẩn cấp', '2025-05-01', 0),
(8, 'Cư dân', 'Phun thuốc diệt muỗi', 'Ban quản lý sẽ phun thuốc diệt muỗi toàn khu lúc 6h sáng ngày 3/5, đề nghị đóng cửa sổ.', 'Chung', '2025-05-01', 0);

UPDATE bills b
JOIN (
    SELECT bill_id, SUM(quantity * s.price) AS total
    FROM bill_Detail_Users d
    JOIN services s ON d.service_id = s.service_id
    GROUP BY bill_id
) AS user_total ON b.bill_id = user_total.bill_id
SET b.total_amount = user_total.total;

UPDATE bills b
JOIN (
    SELECT bill_id, SUM(price * quantity) AS total
    FROM bill_Detail_Managers
    GROUP BY bill_id
) AS manager_total ON b.bill_id = manager_total.bill_id
SET b.total_amount = manager_total.total;


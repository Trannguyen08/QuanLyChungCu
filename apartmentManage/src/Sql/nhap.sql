-- 1. Thêm cư dân mới
INSERT INTO residents (user_id, full_name, phone_number, email, id_card, date_of_birth, gender, apartment_id)  
VALUES (3, 'Nguyễn Văn A', '0987654321', 'nguyenvana@example.com', '123456789012', '1990-05-15', 'Nam', 10);

-- 2. Lấy ID của cư dân vừa thêm
SET @resident_id = LAST_INSERT_ID();

-- 3. Thêm hợp đồng
INSERT INTO contracts (apartment_id, resident_id, created_by, contract_type, start_date, end_date, total_value, payment_status, contract_status)  
VALUES (10, @resident_id, 'Quản lý', 'Mua bán', '2024-03-01', '2029-03-01', 1500000000, 'Chưa thanh toán', 'Hiệu lực');
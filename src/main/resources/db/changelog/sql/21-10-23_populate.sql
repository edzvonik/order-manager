--liquibase formatted sql

--changeSet edzvonik:2
INSERT INTO "Product"(id, name, price) VALUES
    (1, 'Флеш-накопитель 64 Гб', 20.00),
    (2, 'Монитор Dell UltraSharp 27"', 450.00),
    (3, 'Смартфон Samsung Galaxy S22', 799.00),
    (4, 'Планшет Apple iPad Pro 12.9"', 999.00),
    (5, 'Клавиатура Logitech MX Keys', 99.00),
    (6, 'Мышь Logitech MX Master 3', 89.00),
    (7, 'Наушники Bose QuietComfort 45', 299.00),
    (8, 'Фотокамера Canon EOS 5D Mark IV', 2499.00),
    (9, 'Принтер HP LaserJet Pro M402n', 199.00);

INSERT INTO "Order"(id, order_date) VALUES
    (1, '2023-10-20'),
    (2, '2023-10-21'),
    (3, '2023-10-22'),
    (4, '2023-10-23'),
    (5, '2023-10-24'),
    (6, '2023-10-25'),
    (7, '2023-10-26'),
    (8, '2023-10-27'),
    (9, '2023-10-28'),
    (10, '2023-10-29'),
    (11, '2023-10-30'),
    (12, '2023-10-31'),
    (13, '2023-11-01'),
    (14, '2023-11-02'),
    (15, '2023-11-03'),
    (16, '2023-11-04'),
    (17, '2023-11-05'),
    (18, '2023-11-06'),
    (19, '2023-11-07'),
    (20, '2023-11-08');

INSERT INTO "OrderItem"(id, order_id, product_id, quantity, total_price) VALUES
    (1, 1, 1, 2, 20.00),  -- 2 Флеш-накопитель 64 Гб
    (2, 1, 3, 1, 250.00),  -- 1 Наушники Sony WH-1000XM4

    (3, 2, 2, 1, 1500.00),  -- 1 Ноутбук Dell XPS

    (4, 3, 1, 1, 20.00),  -- 1 Флеш-накопитель 64 Гб
    (5, 3, 2, 2, 1500.00),  -- 2 Ноутбука Dell XPS

    (6, 4, 1, 2, 20.00),    -- Флеш-накопитель 64 Гб
    (7, 4, 2, 1, 450.00),   -- Монитор Dell UltraSharp 27"
    
    (8, 5, 3, 1, 799.00),   -- Смартфон Samsung Galaxy S22
    (9, 5, 4, 1, 999.00),   -- Планшет Apple iPad Pro 12.9"
    
    (10, 6, 5, 1, 99.00),    -- Клавиатура Logitech MX Keys
    (11, 6, 6, 2, 89.00),    -- Мышь Logitech MX Master 3
    
    (12, 7, 7, 1, 299.00),   -- Наушники Bose QuietComfort 45
    (13, 7, 8, 1, 2499.00),  -- Фотокамера Canon EOS 5D Mark IV
    
    (14, 8, 9, 1, 199.00),   -- Принтер HP LaserJet Pro M402n
    (15, 8, 1, 3, 20.00),    -- Флеш-накопитель 64 Гб
    
    (16, 9, 2, 1, 450.00),   -- Монитор Dell UltraSharp 27"
    (17, 9, 3, 1, 799.00),   -- Смартфон Samsung Galaxy S22
    
    (18, 10, 4, 1, 999.00),  -- Планшет Apple iPad Pro 12.9"
    (19, 10, 5, 1, 99.00),   -- Клавиатура Logitech MX Keys
    
    (20, 11, 6, 1, 89.00),   -- Мышь Logitech MX Master 3
    (21, 11, 7, 1, 299.00),  -- Наушники Bose QuietComfort 45
    
    (22, 12, 8, 1, 2499.00), -- Фотокамера Canon EOS 5D Mark IV
    (23, 12, 9, 1, 199.00),  -- Принтер HP LaserJet Pro M402n
    
    (24, 13, 1, 2, 20.00),   -- Флеш-накопитель 64 Гб
    (25, 13, 2, 1, 450.00),  -- Монитор Dell UltraSharp 27"
    
    (26, 14, 3, 1, 799.00),  -- Смартфон Samsung Galaxy S22
    (27, 14, 4, 1, 999.00),  -- Планшет Apple iPad Pro 12.9"
    
    (28, 15, 5, 1, 99.00),   -- Клавиатура Logitech MX Keys
    (29, 15, 6, 2, 89.00),   -- Мышь Logitech MX Master 3
    
    (30, 16, 7, 1, 299.00),  -- Наушники Bose QuietComfort 45
    (31, 16, 8, 1, 2499.00), -- Фотокамера Canon EOS 5D Mark IV
    
    (32, 17, 9, 1, 199.00),  -- Принтер HP LaserJet Pro M402n
    (33, 17, 1, 2, 20.00),   -- Флеш-накопитель 64 Гб
    
    (34, 18, 2, 1, 450.00),  -- Монитор Dell UltraSharp 27"
    (35, 18, 3, 1, 799.00),  -- Смартфон Samsung Galaxy S22
    
    (36, 19, 4, 1, 999.00),  -- Планшет Apple iPad Pro 12.9"
    (37, 19, 5, 1, 99.00),   -- Клавиатура Logitech MX Keys
    
    (38, 20, 6, 1, 89.00),   -- Мышь Logitech MX Master 3
    (39, 20, 7, 1, 299.00);  -- Наушники Bose QuietComfort 45


insert INTO member (ID, created_date, modified_date, email, name, password, role)
VALUES  (1,'2023-01-01 00:00:00.200003','2023-01-01 00:00:01.200003','test@mail.com','테스트','$2a$10$/q7M28ED/YvXY1XOUqTDiO/HUUnckWVMGc1NuKKH3Sj2r6wk3hk1m','GUEST');

insert INTO products (ID, created_date, modified_date, barcode_id, brand, price, product_name, store, unit, volume_height, volume_long, volume_short, weight)
VALUES  (2, '2023-01-01 00:00:01.200003', '2023-01-01 00:00:01.200003', '48329482938492','Apple','400','MacBook Pro',
         'costco', 'kg', '40','20','2','1.4'),
        (4, '2023-01-01 00:00:01.200005', '2023-01-01 00:00:01.200005', '483294829384921','Samsung','390','Galaxy Tab' ||
                                                                                                          ' S',
         'costco', 'kg', '40','20','2','1.4');

insert INTO stock_log (stock_id, created_date, barcode_id, in_stock, name, stock_add, stock_sub)
VALUES (3, '2023-01-02 00:00:01.200003', '48329482938492', '100','테스트', '0', '0'),
       (5, '2023-01-03 00:00:01.200003', '483294829384921', '100','테스트', '0', '0');
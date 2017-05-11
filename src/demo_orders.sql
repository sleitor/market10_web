UPDATE demo.orders
SET uuid_user = 1, date = '2017-03-19', cost = 80, status = 'Доставка'
WHERE uuid = 1;
UPDATE demo.orders
SET uuid_user = 14, date = '2017-05-02', cost = 115, status = 'Выполнен'
WHERE uuid = 8;
UPDATE demo.orders
SET uuid_user = 14, date = '2017-05-03', cost = 275, status = 'Новый'
WHERE uuid = 9;
UPDATE demo.orders
SET uuid_user = 213, date = '2017-05-03', cost = 1487, status = 'Выполнен'
WHERE uuid = 10;
UPDATE demo.orders
SET uuid_user = 14, date = '2017-05-03', cost = 615, status = 'Новый'
WHERE uuid = 11;
UPDATE demo.orders
SET uuid_user = 213, date = '2017-05-06', cost = 35, status = 'Новый'
WHERE uuid = 12;
UPDATE demo.orders
SET uuid_user = 218, date = '2017-05-07', cost = 115, status = 'Новый'
WHERE uuid = 13;
UPDATE demo.orders
SET uuid_user = 213, date = '2017-05-09', cost = 105, status = 'Новый'
WHERE uuid = 14;
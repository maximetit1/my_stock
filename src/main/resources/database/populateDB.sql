INSERT INTO public.stock
("name")
 VALUES
('Склад #1'),
('Склад #2'),
('Склад #3');

INSERT INTO public.product
(art, "name", last_purch, last_sale)
VALUES
(123456, 'Товар1', 10.05, 11.00),
(222222, 'Товар2', 11.11, 22),
(654321, 'Товар3', 100.00, 110.5),
(000000, 'Товар4', 5000.00, 6000.00);

INSERT INTO public.users
(username, "password")
VALUES('admin', '$2a$10$AjHGc4x3Nez/p4ZpvFDWeO6FGxee/cVqj5KHHnHfuLnIOzC5ag4fm');

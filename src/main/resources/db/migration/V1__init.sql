create table categories (id bigserial primary key, title varchar(255));
insert into categories (title) values
('Мясо'),
('Шоколад'),
('Молочное'),
('Выпечка');

create table products (id bigserial primary key, title varchar(255), price int, category_id bigint references categories (id));
insert into products (title, price, category_id) values
('Хлеб', 25, 4),
('Сахар', 80, 2),
('Сникерс', 50, 2),
('Шаньга', 30, 4),
('Котлетка', 325, 1),
('Пельмени', 350, 1),
('Сыр', 300, 3),
('Молоко', 60, 3),
('Торт 1', 400, 4),
('Торт 2', 550, 4),
('Масло', 120, 3),
('Баунти', 60, 2),
('Киткат', 55, 2),
('Ватрушка', 34, 4),
('Батон', 40, 4),
('Баранки', 55, 4),
('Кефир', 50, 3),
('Йогурт', 25, 3),
('Баранина', 500, 1),
('Чебупели', 112, 1),
('Сыворотка', 70, 3),
('Сосиски', 345, 1);
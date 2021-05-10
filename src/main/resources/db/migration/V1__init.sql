create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

create table permissions (
  id                    serial,
  name                  varchar(50) not null,
  role_id               int not null,
  primary key (id),
  foreign key (role_id) references roles (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_MANAGER'), ('ROLE_JUN_DEV'), ('ROLE_MID_DEV'), ('ROLE_SEN_DEV');

insert into permissions (name, role_id)
values
('MANAGE_SCHEDULE', 1), ('MANAGE_BALANCE', 1), ('MANAGE_TASK', 1),
('GIT_PULL', 2), ('GIT_COMMIT', 2),
('GIT_PULL', 3), ('GIT_COMMIT', 3), ('GIT_PUSH', 3),
('GIT_PULL', 4), ('GIT_COMMIT', 4), ('GIT_PUSH', 4), ('VIEW_TASKS', 4);

insert into users (username, password, email)
values
('manager', '$2y$12$JXHbM0c2osL8ijReZgf3n.Vl7M.uiqEOLhif0xJTzpWj0sC11x7Tm', 'user@ya.ru'),
('junDev', '$2y$12$JXHbM0c2osL8ijReZgf3n.Vl7M.uiqEOLhif0xJTzpWj0sC11x7Tm', 'dev1@ya.ru'),
('midDev', '$2y$12$JXHbM0c2osL8ijReZgf3n.Vl7M.uiqEOLhif0xJTzpWj0sC11x7Tm', 'dev2@ya.ru'),
('senDev', '$2y$12$JXHbM0c2osL8ijReZgf3n.Vl7M.uiqEOLhif0xJTzpWj0sC11x7Tm', 'dev3@ya.ru');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 3),
(4, 4);
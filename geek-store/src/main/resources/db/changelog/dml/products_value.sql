--liquibase formatted sql
--changeset Roman88:Insert_data

 insert into categories (title)
 values ('Food');

insert into products (title, cost, category_id)
values ('Bread', 40, 1),
       ('Milk', 80, 1),
       ('Eat', 100, 1),
       ('Fish', 150, 1),
       ('Sugar', 70, 1),
       ('Eggs', 90, 1),
       ('Apple', 60, 1),
       ('Pork', 200, 1),
       ('Flour', 50, 1),
       ('Vermicelli', 45, 1),
       ('Salt', 25, 1),
       ('Carrot', 30, 1),
       ('Onion', 20, 1),
       ('Olive oil', 140, 1),
       ('Potato', 50, 1),
       ('Chicken', 150, 1),
       ('Tomatoes', 80, 1),
       ('Sausage', 120, 1),
       ('Cheese', 100, 1),
       ('Dumplings', 130, 1);

insert into users(username, password, email, enthusiasm)
values
    ('Bob', '$2a$12$3gW6p2O3C9ufSMBHLDsuHOKulDKNu2iyvo1CEFuhSudeQQzdGOKR6', 'bob18@mail.ru', 'football'),
    ('John', '$2a$12$jZSwdf7wsnu3RvUJ4YtB7.BePRAab/Lm3bQNFQPFeyyxBnzqnyVc2', 'john99@mail.ru', 'dance');

insert into roles(name)
values
    ('ROLE_USER'), ('ROLE_ADMIN');

insert into users_roles (user_id, role_id)
values
    (1, 2), (2, 1);






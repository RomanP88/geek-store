--liquibase formatted sql

--changeset roman88:create_products_table

create table categories
(
    id    bigserial primary key,
    title varchar(255)
);


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    cost        int,
    category_id bigint references categories (id)
);

create table users
(
    id              bigserial primary key,
    username        varchar(30) unique,
    password        varchar(100) not null,
    email           varchar(50) unique,
    enthusiasm      varchar(70),
    created_at timestamp default  current_timestamp,
    updated_at timestamp default  current_timestamp
);


create table roles
(
    id          serial primary key ,
    name        varchar(50) not null,
    created_at timestamp default  current_timestamp,
    updated_at timestamp default  current_timestamp
);

create table users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

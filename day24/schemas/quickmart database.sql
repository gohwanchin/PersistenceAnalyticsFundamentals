-- drop database if exists
drop schema if exists quickmart;

-- create database
create schema quickmart;
use quickmart;

create table purchase_order(
    order_id char(8) not null,
    name varchar(64) not null,
    order_date date default (current_date),

    primary key(order_id)
);

create table sku(
    prod_id int not null auto_increment,
    description varchar(256) not null,
    unit_price float(5,2),

    primary key(prod_id)
);

create table line_item(
    item_id int not null auto_increment,
    quantity int default '1',
    
    order_id char(8),
    prod_id int,

    primary key(item_id),
    constraint fk_order_id
        foreign key(order_id)
        references purchase_order(order_id),
    constraint fk_prod_id
        foreign key(prod_id)
        references sku(prod_id)
);
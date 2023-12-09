drop database if exists todo;

create database todo;
use todo;

create table user(
    user_id char(8) not null,
    username varchar(32) not null,
    name varchar(32),

    primary key(user_id)
);

create table task(
    task_id int not null auto_increment,
    description varchar(255),
    priority int not null,
    due_date date,
    user_id char(8) not null,

    primary key(task_id),
    constraint fk_user_id
        foreign key(user_id)
        references user(user_id)
);
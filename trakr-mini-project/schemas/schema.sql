drop database if exists trakr;

create database trakr;
use trakr;

create table users(
    username varchar(32) not null,
    password varchar(128) not null,

    primary key(username)
);

create table watchlists(
    username varchar(128) not null,
    id varchar(32) not null,
    date_added date not null,

    primary key(username, id),
    constraint fk_email foreign key(username) references users(username)
);
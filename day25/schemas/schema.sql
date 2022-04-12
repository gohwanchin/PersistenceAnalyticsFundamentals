drop database if exists accounts;

create database accounts;
use accounts;

create table user(
    username varchar(32) not null,
    password varchar(128) not null,

    primary key(username)
);
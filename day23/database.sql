-- drop database if exists
drop database if exists addressbook;

-- create new database
create database addressbook;

-- select database
use addressbook;

-- create table
create table bff(
    email varchar(128) not null,
    name varchar(128) not null,
    phone varchar(16),
    dob date,
    status enum('friend','foe') default 'friend',
    passphrase varchar(128),
    primary key(email)
);
drop database if exists socialmedia;

create database socialmedia;

use socialmedia;

create table post(
    post_id int not null auto_increment,
    photo varchar(256),
    comment mediumtext,
    uploader varchar(64),
    mediatype varchar(256),

    primary key(post_id)
);
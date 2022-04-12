-- Delete data if exists
truncate table user;

insert into user(username,password) values
    ('admin',sha1('administrator')),
    ('cait',sha1('vi')),
    ('jane',sha1('tennant')),
    ('kate',sha1('whistler')),
    ('lucy',sha1('tara'));
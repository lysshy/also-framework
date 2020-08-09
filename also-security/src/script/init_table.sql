drop table if exists sys_user;
create table sys_user
(
    id serial8 primary key,
    username varchar(50),
    password varchar(100),
    create_time timestamp,
    update_time timestamp
);

drop table if exists sys_role;
create table sys_role
(
    id serial8 primary key,
    role_name varchar(50),
    create_time timestamp,
    update_time timestamp
);

drop table if exists sys_user_role;
create table sys_user_role
(
    id serial8 primary key,
    role_id int8,
    user_id int8
);

drop table if exists sys_user;
create table sys_user
(
    id serial8 primary key,
    username varchar(50),
    password varchar(100),
    create_time timestamp,
    create_id int8,
    update_time timestamp,
    update_id int8,
    remark varchar(200)
);

comment on table sys_user is '系统用户表';
comment on column sys_user.id is '主键ID';
comment on column sys_user.username is '用户名';
comment on column sys_user.password is '密码';
comment on column sys_user.create_time is '创建时间';
comment on column sys_user.create_id is '创建者id';
comment on column sys_user.update_time is '更新时间';
comment on column sys_user.update_id is '更新者id';
comment on column sys_user.remark is '备注';



drop table if exists sys_role;
create table sys_role
(
    id serial8 primary key,
    name varchar(50),
    code varchar(50),
    create_time timestamp,
    create_id int8,
    update_time timestamp,
    update_id int8,
    remark varchar(200)
);
comment on table sys_role is '系统角色表';
comment on column sys_role.id is '主键ID';
comment on column sys_role.name is '角色名';
comment on column sys_role.code is '角色编码';
comment on column sys_role.create_time is '创建时间';
comment on column sys_role.create_id is '创建者id';
comment on column sys_role.update_time is '更新时间';
comment on column sys_role.update_id is '更新者id';
comment on column sys_role.remark is '备注';



drop table if exists sys_user_role;
create table sys_user_role
(
    id serial8 primary key,
    role_id int8,
    user_id int8
);
comment on table sys_user_role is '用户角色关联表';
comment on column sys_user_role.id is '主键ID';
comment on column sys_user_role.role_id is '角色id';
comment on column sys_user_role.user_id is '用户id';


drop table if exists sys_permission;
create table sys_permission
(
    id serial8 primary key,
    name varchar(100),
    code varchar(50),
    type int2,
    url varchar(500),
    method_type varchar(20),
    sort int4,
    parent_id int8
);
comment on table sys_permission is '权限表表';
comment on column sys_permission.id is '主键ID';
comment on column sys_permission.name is '权限名称';
comment on column sys_permission.code is '权限编码，权限表达式';
comment on column sys_permission.type is '权限类型，页面-1，按钮-2';
comment on column sys_permission.url is '权限地址，类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址';
comment on column sys_permission.method_type is '方法类型，例如POST，GET等';
comment on column sys_permission.sort is '顺序';
comment on column sys_permission.parent_id is '父节点ID';


drop table if exists sys_role_permission;
create table sys_role_permission
(
    id serial8 primary key,
    role_id int8,
    permission_id int8
);
comment on table sys_role_permission is '角色权限关联表';
comment on column sys_role_permission.id is '主键ID';
comment on column sys_role_permission.role_id is '角色id';
comment on column sys_role_permission.permission_id is '权限id';
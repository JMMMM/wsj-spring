create table new_wsj.customers(
 `id` int(8) not null comment '用户ID' primary key AUTO_INCREMENT,
 `name` varchar(255) not null comment '用户名,昵称',
 `login_name` varchar(255) not null comment '登陆账号',
 `password` varchar(255) not null comment '登陆密码',
 `phone` varchar(255) default null comment '手机号码',
 `sex` tinyint(1) default null comment '性别',
 `created_at` datetime not null comment '创建时间',
 `created_by` int(8) default null comment '创建人',
 `updated_at` datetime not null comment '更新时间',
 `updated_by` int(8) default null comment '更新人'
) comment '用户登陆表';
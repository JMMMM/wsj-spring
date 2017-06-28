drop table if exists new_wsj.customers;
create table new_wsj.customers(
 `id` int(8) not null comment '用户ID' primary key AUTO_INCREMENT,
 `name` varchar(255) not null comment '用户名,昵称',
 `login_name` varchar(255) not null comment '登陆账号',
 `password` varchar(255) not null comment '登陆密码',
 `phone` varchar(11) default null comment '手机号码',
 `sex` tinyint(1) default null comment '性别,0女，1男',
 `status` tinyint(1) not null default 1 COMMENT '状态，0禁用，1启用',
 `created_at` datetime not null comment '创建时间',
 `created_by` int(8) default null comment '创建人',
 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `updated_by` int(8) default null comment '更新人',
 `last_login_at` datetime default null comment '上次登陆时间',
  unique key `login_name_unique` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户登陆表';

insert INTO  new_wsj.customers(name,login_name,password,phone,created_at,created_by,updated_by) values('假数据','test1','e10adc3949ba59abbe56e057f20f883e','12345678910',now(),1,1);
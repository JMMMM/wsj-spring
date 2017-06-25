drop table if exists new_wsj.staffs;
create table new_wsj.staffs (
 `id` int(8) not null comment '员工ID' primary key AUTO_INCREMENT,
 `name` varchar(255) not null comment '用户名',
 `login_name` varchar(255) not null comment '登陆账号',
 `password` varchar(255) not null comment '登陆密码',
  `sex` tinyint(1) default null comment '性别',
 `status` tinyint(1) not null default 1 comment '0是禁用，1是启用',
 `phone` varchar(255) default null comment '手机号码',
 `created_at` datetime not null comment '创建时间',
 `created_by` int(8) default null comment '创建人,staff_id',
 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `updated_by` int(11) NOT NULL COMMENT '修改者staff_id',
 `last_login_at` datetime default null comment '上次登陆时间',
 unique key `login_name_unique` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment'系统用户登陆表';


insert into new_wsj.staffs(login_name,name,password,created_at,created_by,updated_by) values('admin','管理员','e10adc3949ba59abbe56e057f20f883e',now(),1,1) ;
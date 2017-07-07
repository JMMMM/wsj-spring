drop table if exists new_wsj.sms_log;
create table new_wsj.sms_log(
 `id` int(8) not null comment 'id' primary key AUTO_INCREMENT,
 `identifying_code` varchar(128) not null comment '手机验证码',
 `type` SMALLINT(1) not null comment '类型：1.登陆验证码',
 `phone` varchar(11) default null comment '手机号码',
 `created_at` datetime not null comment '创建时间',
 `created_by` int(8) default null comment '创建人',
 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 `updated_by` int(8) default null comment '更新人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '手机短信记录表';

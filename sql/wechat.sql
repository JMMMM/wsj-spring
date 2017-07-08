drop table if exists new_wsj.wx_access_token;
create table new_wsj.wx_access_token(
  `id` int(8) not null comment 'ID' primary key AUTO_INCREMENT,
  `access_token` varchar(255) comment 'access_token',
  `expires_in` int(8) comment '过期时间',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment'微信access_token记录表';
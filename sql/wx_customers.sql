drop table if exists new_wsj.wx_customers;
create table new_wsj.wx_customers(
 `id` int(8) not null comment '用户ID' primary key AUTO_INCREMENT,
 `subscribe` int(8) default null comment '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
 `openid` varchar(128) not null comment '用户的标识，对当前公众号唯一' UNIQUE ,
 `nickname` varchar(128) not null comment '微信用户昵称' ,
 `nickname_emoji` varchar(128) default null comment '昵称 表情转义',
 `sex` SMALLINT (1) default null comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
 `language` varchar(10) default null comment '语言',
 `province` varchar (128) default null comment '省份',
 `city` varchar (128) default null comment '城市',
 `country` varchar(128) default null comment '国家',
 `head_img_url` varchar(200) default null comment '用户头像',
 `subscribe_time` int(11) default null comment '关注时间',
 `group_id` int(8) default null comment '分组ID',
 `privilege` varchar(128) default null comment '用户特权信息',
 `remark` varchar(128) default null comment '公众号运营者对粉丝的备注',
 `targid_list` varchar(128) default null comment '用户被搭上的标签ID',
 `access_token` varchar(128) DEFAULT  null comment 'access_token',
 `access_token_created_at` datetime default null comment 'access_token_time',
 `refresh_token` varchar(128) default null comment 'refresh_token',
 `refresh_token_created_at` datetime default null comment 'refresh token time',
 `created_at` datetime not null comment '创建时间',
 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '微信用户信息表';
alter table new_wsj.wx_customers convert to character set utf8mb4 collate utf8mb4_bin;

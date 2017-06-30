drop table if exists new_wsj.wx_customers;
create table new_wsj.wx_customers(
 `id` int(8) not null comment '用户ID' primary key AUTO_INCREMENT,
 `subscribe` int(8) not null default 0 comment '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
 `openid` varchar(255) not null comment '用户的标识，对当前公众号唯一',
 `nickname` varchar(255) not null comment '微信用户昵称',
 `nickname_emoji` varchar(255) default null comment '昵称 表情转义',
 `sex` SMALLINT (1) default null comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
 `language` varchar(10) default null comment '语言',
 `province` varchar (255) default null comment '省份',
 `city` varchar (255) default null comment '城市',
 `country` varchar(255) default null comment '国家',
 `head_img_url` varchar(255) default null comment '用户头像',
 `subscribe_time` varchar(255) default null comment '关注时间',
 `group_id` int(8) default null comment '分组ID',
 `privilege` varchar(255) default null comment '用户特权信息',
 `remark` varchar(255) default null comment '公众号运营者对粉丝的备注',
 `targid_list` varchar(255) default null comment '用户被搭上的标签ID'
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '微信用户信息表';

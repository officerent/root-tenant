create database if not exists tenant default charset utf8 collate utf8_general_ci;

use tenant;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) COMMENT '用户名',
  `email` varchar(250) COMMENT '用户邮箱',
  `mobile` varchar(50) COMMENT '用户手机',
  `password` varchar(2048) COMMENT '用户密码，base64+sha1加密以后的值',
  `status` tinyint comment '状态:0=正常，1=冻结，2=删除',
  `last_frozen_time` datetime comment '最后一次冻结时间',
  `create_time` datetime comment '创建时间',
  `creator` varchar(50) COMMENT '创建者',
  `update_time` datetime comment '最后一次更新时间',
  `updator` varchar(50) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `name` (`name`)
) AUTO_INCREMENT = 2000000 ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='用户表';
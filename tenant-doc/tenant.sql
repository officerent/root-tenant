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

CREATE TABLE `organization` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `name` varchar(100) COMMENT '组织名',
  `contact_name` varchar(50) COMMENT '联系人姓名',
  `contact_email` varchar(250) COMMENT '联系人邮箱',
  `contact_mobile` varchar(50) COMMENT '联系人手机',
  `status` tinyint comment '状态:0=待审核，1=正常，2=冻结',
  `last_frozen_time` datetime comment '最后一次冻结时间',
  `create_time` datetime comment '创建时间',
  `creator` varchar(50) COMMENT '创建者',
  `update_time` datetime comment '最后一次更新时间',
  `updator` varchar(50) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) AUTO_INCREMENT = 1000 ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='组织表';

CREATE TABLE `org_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织用户关系ID',
  `org_id` bigint NOT NULL COMMENT '组织ID',
  `user_id` bigint NOT NULL COMMENT '组织ID',
  `role` varchar(100) COMMENT '角色名：admin/user',
  `status` tinyint comment '状态:0=待审核，1=正常，2=删除',
  `create_time` datetime comment '创建时间',
  `creator` varchar(50) COMMENT '创建者',
  `update_time` datetime comment '最后一次更新时间',
  `updator` varchar(50) COMMENT '更新者',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `user_id` (`user_id`)
) AUTO_INCREMENT = 1000 ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='组织用户关系表';

CREATE TABLE `meta_org_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织角色原数据ID',
  `name` varchar(100) COMMENT '角色名：admin/user',
  `auth_list` varchar(300) COMMENT '该角色的权限列表,逗号分割。admin:resource,finance,people; user:resource',
  `create_time` datetime comment '创建时间',
  `creator` varchar(50) COMMENT '创建者',
  `update_time` datetime comment '最后一次更新时间',
  `updator` varchar(50) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) AUTO_INCREMENT = 1000 ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='组织角色元数据表';

CREATE TABLE `meta_org_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织角色原数据ID',
  `name` varchar(100) COMMENT '权限名：resource,finance,people',
  `create_time` datetime comment '创建时间',
  `creator` varchar(50) COMMENT '创建者',
  `update_time` datetime comment '最后一次更新时间',
  `updator` varchar(50) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) AUTO_INCREMENT = 1000 ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='组织权限点元数据表';

insert into meta_org_auth(name,create_time,creator) values('resource',now(),'system');
insert into meta_org_auth(name,create_time,creator) values('finance',now(),'system');
insert into meta_org_auth(name,create_time,creator) values('people',now(),'system');

insert into meta_org_role(name,auth_list,create_time,creator) values('admin','resource,finance,people',now(),'system');
insert into meta_org_role(name,auth_list,create_time,creator) values('user','resource',now(),'system');

CREATE TABLE `meta_org_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织角色原数据ID',
  `name` varchar(100) COMMENT '权限名：resource,finance,people',
  `create_time` datetime comment '创建时间',
  `creator` varchar(50) COMMENT '创建者',
  `update_time` datetime comment '最后一次更新时间',
  `updator` varchar(50) COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) AUTO_INCREMENT = 1000 ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='组织权限点元数据表';



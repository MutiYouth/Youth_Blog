/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.16 : Database - blog
*********************************************************************
*/
CREATE DATABASE IF NOT EXISTS test_blog_210216_youth
    DEFAULT CHARACTER SET utf8;

use test_blog_210216_youth;


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
# CREATE DATABASE /*!32312 IF NOT EXISTS*/`weng_blog_t1` /*!40100 DEFAULT CHARACTER SET utf8 */;

use weng_blog_t1;

/*Table structure for table `blog_article` */
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` int(10) NOT NULL COMMENT '文章类别id',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `author` varchar(32) NOT NULL COMMENT '作者',
  `label` varchar(50) DEFAULT NULL COMMENT '标签',
  `content` text NOT NULL COMMENT '正文',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_no` int(10) unsigned DEFAULT '0' COMMENT '显示顺序,越大越靠前',
  `allow_comment` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '允许评论',
  `is_stick` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '是否置顶',
  `is_draft` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '是否草稿',
  `is_deleted` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `author` (`author`),
  CONSTRAINT `blog_article_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `blog_category` (`id`),
  CONSTRAINT `blog_article_ibfk_2` FOREIGN KEY (`author`) REFERENCES `blog_user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL COMMENT '名称',
  `intro` varchar(200) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_comment` */

DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(10) DEFAULT '0' COMMENT '父id',
  `rootid` int(10) DEFAULT '0' COMMENT '根id',
  `member_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `contact_info` varchar(100) DEFAULT NULL COMMENT '联系信息',
  `content` varchar(200) NOT NULL COMMENT '评论',
  `pdate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `isleaf` int(10) DEFAULT '0' COMMENT '是否叶子节点',
  `article_id` int(10) DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_label` */

DROP TABLE IF EXISTS `blog_label`;
CREATE TABLE `blog_label` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*留言*/
DROP TABLE IF EXISTS `blog_letter`;
CREATE TABLE `blog_letter` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `contact_way` varchar(30) NOT NULL COMMENT '联系方式',
  `content` text NOT NULL COMMENT '正文',
  `view_status` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '查阅状态',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Table structure for table `blog_link` */
DROP TABLE IF EXISTS `blog_link`;
CREATE TABLE `blog_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL COMMENT '名称',
  `url` varchar(100) NOT NULL COMMENT '链接地址',
  `remark` varchar(200) NOT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `blog_notice`;
CREATE TABLE `blog_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL COMMENT '名称',
  `content` varchar(200) NOT NULL COMMENT '内容',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `is_active` tinyint(1) unsigned zerofill DEFAULT '0' COMMENT '是否激活',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `pass_word` varchar(64) NOT NULL COMMENT '密码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(1) unsigned DEFAULT NULL COMMENT '性别',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像地址',
  `sign_msg` varchar(30) DEFAULT NULL COMMENT '个性签名',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# weng_blog_t1.blog_user
# weng/abc123456
INSERT INTO blog_user (user_name,nickname, pass_word,email, sex,city,avatar, sign_msg, gmt_create, gmt_modified) VALUES
  ('weng','Murphy Liu','31b92710645ada1d26b1c1f07d84a830','xxx@126,com',0,'杭州','/YOUTH_BLOG2/upload/avatar/weng_avatar.jpg','加油吧，骚年！', '2018-06-14 18:03:19', '2018-06-14 18:11:56');



# Blog 的权限等
/*-------------------------------------------------------------------*/
DROP TABLE IF EXISTS `blog_permission`;
DROP TABLE IF EXISTS `blog_role`;
DROP TABLE IF EXISTS `blog_role_permission`;
DROP TABLE IF EXISTS `blog_user_role`;

CREATE TABLE `blog_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) NOT NULL COMMENT '权限编码',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `blog_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '角色名',
  `code` varchar(30) NOT NULL COMMENT '角色编号',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `blog_role_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(10) NOT NULL COMMENT '角色ID',
  `permission_id` int(10) NOT NULL COMMENT '权限ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

# 用户权限配置表
CREATE TABLE `blog_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) NOT NULL COMMENT '用户名',
  `role_id` int(10) NOT NULL COMMENT '角色名',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


INSERT INTO `blog_permission` (id,code ) VALUES (1,'目录'), (2,'文章'), (3,'链接'),(4,'公告');
INSERT INTO `blog_role` (id,name,code ) VALUES (1,'admin','admin'), (2,'writer','writer'), (3,'reader','reader');
INSERT INTO blog_role_permission (id,role_id,permission_id ) VALUES (1,1,1), (2,1,2), (3,1,3), (4,1,4), (5,2,2);
INSERT INTO blog_user_role (user_id,role_id ) VALUES (1,1);





/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

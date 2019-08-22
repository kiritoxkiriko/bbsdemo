create database bbs;
use bbs;
create table board
(
  boardid       bigint auto_increment
    primary key,
  boardname     varchar(50) charset utf8   not null comment '板块名称',
  info          varchar(1000) charset utf8 not null comment '板块信息，存入json',
  post_num      bigint default 0           null,
  postreply_num bigint default 0           null,
  notice        varchar(1000) charset utf8 null comment '板块公告'
)
  comment '板块';

create table posttheme
(
  posttheme_id bigint auto_increment
    primary key,
  themename    varchar(20) charset utf8 not null comment '主题名称',
  boardid      bigint                   not null comment '生效板块id',
  constraint theme_board_id_fk
    foreign key (boardid) references board (boardid)
)
  comment '帖子主题';

create table user
(
  userid                 bigint auto_increment comment '用户id'
    primary key,
  username               varchar(20)                  not null comment '用户名',
  nickname               varchar(50) charset utf8     not null comment '昵称',
  password               varchar(200)                 not null comment '密码',
  mail                   varchar(50)                  not null comment '邮箱',
  type                   varchar(15) default 'normal' not null comment '用户类型',
  level                  int         default 1        not null comment '用户等级 1-10',
  point                  bigint      default 0        not null comment '积分',
  info                   varchar(20000) charset utf8  null comment '用户介绍，存入json格式字符串',
  moderator_of           bigint                       null comment '为板块id为此值的版主(空值则不为任何板块的版主)',
  assistant_moderator_of bigint                       null comment '为板块id为此值的小版主(空值则不为任何板块的小版主)',
  is_private             tinyint(1)  default 0        null comment '是否为私有账号',
  constraint user_mail_uindex
    unique (mail),
  constraint user_nickname_uindex
    unique (nickname),
  constraint user_username_uindex
    unique (username),
  constraint user_board_id_fk
    foreign key (moderator_of) references board (boardid),
  constraint user_board_id_fk_2
    foreign key (assistant_moderator_of) references board (boardid)
);

create table banned
(
  bannedid      bigint auto_increment
    primary key,
  banneduser_id bigint           not null comment '被禁言的用户',
  bannedbord_id bigint           not null comment '被ban板块',
  time          bigint default 0 not null comment '被ban时间，毫秒',
  starttime     date             not null comment '禁言开始时间',
  endtime       datetime         not null comment '禁言结束时间',
  constraint banned_board_id_fk
    foreign key (bannedbord_id) references board (boardid),
  constraint banned_user_id_fk
    foreign key (banneduser_id) references user (userid)
)
  comment '禁言用户';

create table bannedmsg
(
  bannedmsg_id  int auto_increment comment 'id'
    primary key,
  banneduser_id bigint not null comment '被屏蔽的用户id',
  bannedby_id   bigint not null comment '屏蔽人id',
  constraint bannedmsg_user_id_fk
    foreign key (banneduser_id) references user (userid),
  constraint bannedmsg_user_id_fk_2
    foreign key (bannedby_id) references user (userid)
)
  comment '用户屏蔽的私信';

create table message
(
  messageid   int auto_increment
    primary key,
  senderid    bigint                      not null comment '发件人id',
  receiverid  bigint                      not null comment '收件人id',
  message     varchar(20000) charset utf8 not null comment '私信内容',
  senddate    datetime                    not null comment '发送时间',
  is_received tinyint(1) default 0        not null comment '是否接受',
  is_notice   tinyint(1) default 0        null comment '是否为公告',
  constraint message_user_id_fk
    foreign key (senderid) references user (userid),
  constraint message_user_id_fk_2
    foreign key (receiverid) references user (userid)
)
  comment '私信';

create table post
(
  postid        bigint auto_increment comment '帖子id'
    primary key,
  boardid       bigint                   not null comment '所在版块id',
  name          varchar(50) charset utf8 not null comment '帖子名称',
  themeid       bigint                   not null comment '主题id',
  postby_id     bigint                   not null comment '发帖id',
  postdate      datetime                 not null comment '发帖日期',
  viewcount     bigint default 0         not null comment '浏览次数',
  postreply_num bigint default 1         null,
  constraint post_board_boardid_fk
    foreign key (boardid) references board (boardid),
  constraint post_ibfk_1
    foreign key (postby_id) references user (userid)
)
  comment '帖子';

create index postid
  on post (postby_id);

create table postreply
(
  postreply_id bigint auto_increment comment '帖子回复编号'
    primary key,
  postid       bigint                      not null comment '帖子编号',
  replyby_id   bigint                      null comment '回复人id',
  replydate    datetime                    not null comment '回帖时间',
  content      varchar(20000) charset utf8 not null comment '回复内容',
  floornum     int                         not null comment '楼层',
  modifyby_id  bigint                      null comment '最后一个用户修改id',
  modifydate   datetime                    null comment '最后修改时间',
  constraint postreply_post_id_fk
    foreign key (postid) references post (postid),
  constraint postreply_user_id_fk
    foreign key (modifyby_id) references user (userid),
  constraint postreply_user_id_fk_2
    foreign key (replyby_id) references user (userid)
)
  comment '帖子回复';

create table postreply_comment
(
  postreply_comment_id bigint auto_increment comment '评论id'
    primary key,
  postreply_id         bigint                    not null comment '所在帖子回复id',
  commentby_id         bigint                    not null comment '发送回复用户id',
  comment              varchar(100) charset utf8 not null comment '回复内容，不超过100字',
  commentdate          datetime                  not null comment '回复时间',
  constraint postreply_comment_postreply_id_fk
    foreign key (postreply_id) references postreply (postreply_id),
  constraint postreply_comment_user_id_fk
    foreign key (commentby_id) references user (userid)
)
  comment '帖子回复评论';

insert into user VALUES (1,'system','系统','','','system',10,10000,'',null,null,1);
UPDATE `bbs`.`user` t SET t.`userid` = 0 WHERE t.`userid` = 1;
insert into user VALUES (2,'admin','管理员','e10adc3949ba59abbe56e057f20f883e','master@wxmpoi.xyz1','admin',10,10000,'',null,null,1);
INSERT INTO board VALUES (1,'求助', '求助板块', DEFAULT, DEFAULT, '本版块为求助板块');
INSERT INTO board VALUES (2,'讨论', '讨论板块', DEFAULT, DEFAULT, '本版块为讨论板块');
INSERT INTO board VALUES (3, '', '', 0, 0, '');
UPDATE `bbs`.`board` t SET t.`boardid` = 0 WHERE t.`boardid` = 3;
INSERT INTO posttheme (themename,boardid) VALUES ('求助', 1);
INSERT INTO posttheme (themename,boardid) VALUES ('讨论', 2);





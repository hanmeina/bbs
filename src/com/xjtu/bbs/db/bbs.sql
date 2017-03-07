drop database if exists bbs;
create database if not exists bbs;
use bbs;

drop table if exists user;
drop table if exists reply;
drop table if exists topic;
drop table if exists type;
drop table if exists admin;
drop table if exists address;
drop table if exists flow;

create table user(
 id int primary key auto_increment,
 username varchar(20) not null,
 password varchar(40) not null,
 gender varchar(6) not null,
 email varchar(30) not null
);
create table admin(
 id int primary key auto_increment,
 name varchar(20) not null,
 title varchar(50) not null unique 
);
insert into admin(name,title) values('王强','汽车');
insert into admin(name,title) values('王强','电脑');
insert into admin(name,title) values('李渊','游戏');
insert into admin(name,title) values('李渊','房子');
insert into admin(name,title) values('何梅','手机');

create table type(
 id int primary key auto_increment,
 title varchar(50) not null unique, 
 image varchar(50) not null unique, 
 click int not null default 0,
 constraint title_FK foreign key(title) references admin(title)
);
insert into type(title,image) values('汽车','image/car.jpg');
insert into type(title,image) values('电脑','image/computer.jpg');
insert into type(title,image) values('游戏','image/game.jpg');
insert into type(title,image) values('房子','image/house.jpg');
insert into type(title,image) values('手机','image/phone.jpg');

create table topic(
 id int primary key auto_increment,
 title varchar(50) not null unique, 
 name varchar(50) not null, 
 content varchar(200) not null,
 time timestamp not null,
 type_id int,
 constraint type_id_FK foreign key(type_id) references type(id) 
);
insert into topic(title,name,content,type_id) values('大众汽车降价了','张兵','庆五一，大众汽车在中国市场全线价格下降30%，仅限5万辆',1);
insert into topic(title,name,content,type_id) values('汽车油价上涨了','何平','受国际油价关系，中国市场全线油价上调10%',1);
insert into topic(title,name,content,type_id) values('移动电脑','李渊','微软决定进军移动领域，开发专用于移动领域个人电脑',2);
insert into topic(title,name,content,type_id) values('迷你型个人电脑','赵达','苹台开发出迷你型个人电脑，仅有普通手表大小',2);
insert into topic(title,name,content,type_id) values('三国游戏上线了','河平','庆十一，三国游戏上线了，免费玩7天',3);
insert into topic(title,name,content,type_id) values('最新足球游戏','南安','庆十一，邀请游戏玩家一共聚会',3);
insert into topic(title,name,content,type_id) values('广州房价平横','郑海搏','2011年上半年，广州新房价格平稳，大浮降价的空间不大',4);
insert into topic(title,name,content,type_id) values('北京房价下降很慢','郑海搏','受国家调空的影响，北京二季度楼市缓慢下降',4);
insert into topic(title,name,content,type_id) values('中国联通iPhone','谭坤','庆国庆，中国联通iPhone仅限10万部手机优惠',5);
insert into topic(title,name,content,type_id) values('中国移动oPhone','刘志','庆国庆，中国移动oPhone手机优惠20%，欲购从速',5);

create table reply(
 id int primary key auto_increment,
 title varchar(50) not null unique, 
 name varchar(20) not null, 
 content varchar(200) not null,
 time timestamp not null,
 topic_id int,
 constraint topic_id_FK foreign key(topic_id) references topic(id) 
);
insert into reply(title,name,content,topic_id) 
values('大众汽车现在多少钱呀','小雨','大众汽车现在多少钱呀',1);

insert into reply(title,name,content,topic_id) 
values('97号汽油涨了吗','小雨','97号汽油涨了吗',2);

insert into reply(title,name,content,topic_id) 
values('电脑还是移动的好','小雨','电脑还是移动的好',3);

insert into reply(title,name,content,topic_id) 
values('太了电脑，我都快看不清了','小清','太了电脑，我都快看不清了',4);

create table address(
 id int primary key auto_increment,
 ip varchar(20) not null,
 location varchar(20) not null
);
insert into address(ip,location) values('127.0.0.1','广州');

create table flow(
 id int primary key auto_increment,
 historydate date not null,
 num int not null default 200
);
insert into flow(historydate) values('2011-08-16');

#普通用户表
select * from user;
#管理员表
select * from admin;
#版块表
select * from type;
#主题表
select * from topic;
#回复表
select * from reply;
#归属地表
select * from address;
#流量表
select * from flow;
#需要的话，再增加表








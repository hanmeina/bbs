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
insert into admin(name,title) values('��ǿ','����');
insert into admin(name,title) values('��ǿ','����');
insert into admin(name,title) values('��Ԩ','��Ϸ');
insert into admin(name,title) values('��Ԩ','����');
insert into admin(name,title) values('��÷','�ֻ�');

create table type(
 id int primary key auto_increment,
 title varchar(50) not null unique, 
 image varchar(50) not null unique, 
 click int not null default 0,
 constraint title_FK foreign key(title) references admin(title)
);
insert into type(title,image) values('����','image/car.jpg');
insert into type(title,image) values('����','image/computer.jpg');
insert into type(title,image) values('��Ϸ','image/game.jpg');
insert into type(title,image) values('����','image/house.jpg');
insert into type(title,image) values('�ֻ�','image/phone.jpg');

create table topic(
 id int primary key auto_increment,
 title varchar(50) not null unique, 
 name varchar(50) not null, 
 content varchar(200) not null,
 time timestamp not null,
 type_id int,
 constraint type_id_FK foreign key(type_id) references type(id) 
);
insert into topic(title,name,content,type_id) values('��������������','�ű�','����һ�������������й��г�ȫ�߼۸��½�30%������5����',1);
insert into topic(title,name,content,type_id) values('�����ͼ�������','��ƽ','�ܹ����ͼ۹�ϵ���й��г�ȫ���ͼ��ϵ�10%',1);
insert into topic(title,name,content,type_id) values('�ƶ�����','��Ԩ','΢����������ƶ����򣬿���ר�����ƶ�������˵���',2);
insert into topic(title,name,content,type_id) values('�����͸��˵���','�Դ�','ƻ̨�����������͸��˵��ԣ�������ͨ�ֱ��С',2);
insert into topic(title,name,content,type_id) values('������Ϸ������','��ƽ','��ʮһ��������Ϸ�����ˣ������7��',3);
insert into topic(title,name,content,type_id) values('����������Ϸ','�ϰ�','��ʮһ��������Ϸ���һ���ۻ�',3);
insert into topic(title,name,content,type_id) values('���ݷ���ƽ��','֣����','2011���ϰ��꣬�����·��۸�ƽ�ȣ��󸡽��۵Ŀռ䲻��',4);
insert into topic(title,name,content,type_id) values('���������½�����','֣����','�ܹ��ҵ��յ�Ӱ�죬����������¥�л����½�',4);
insert into topic(title,name,content,type_id) values('�й���ͨiPhone','̷��','����죬�й���ͨiPhone����10���ֻ��Ż�',5);
insert into topic(title,name,content,type_id) values('�й��ƶ�oPhone','��־','����죬�й��ƶ�oPhone�ֻ��Ż�20%����������',5);

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
values('�����������ڶ���Ǯѽ','С��','�����������ڶ���Ǯѽ',1);

insert into reply(title,name,content,topic_id) 
values('97������������','С��','97������������',2);

insert into reply(title,name,content,topic_id) 
values('���Ի����ƶ��ĺ�','С��','���Ի����ƶ��ĺ�',3);

insert into reply(title,name,content,topic_id) 
values('̫�˵��ԣ��Ҷ��쿴������','С��','̫�˵��ԣ��Ҷ��쿴������',4);

create table address(
 id int primary key auto_increment,
 ip varchar(20) not null,
 location varchar(20) not null
);
insert into address(ip,location) values('127.0.0.1','����');

create table flow(
 id int primary key auto_increment,
 historydate date not null,
 num int not null default 200
);
insert into flow(historydate) values('2011-08-16');

#��ͨ�û���
select * from user;
#����Ա��
select * from admin;
#����
select * from type;
#�����
select * from topic;
#�ظ���
select * from reply;
#�����ر�
select * from address;
#������
select * from flow;
#��Ҫ�Ļ��������ӱ�








use kbms;
create table incident(
`id` int auto_increment,
`province` varchar(20),
`city` varchar(20),
`begin_time` datetime,
`end_time` datetime,
`influence` varchar(50),
`grade` varchar(20),
`keyword` varchar(20),
`content` mediumtext,
`incidentname` varchar(20),
primary key(`id`))engine=innodb default charset=utf8;
create table customer(
`user_id` int,
`password` varchar(20),
`identity` varchar(20),
`score` int,
primary key(`user_id`))engine=innodb default charset=utf8;
create table checkincident(
`id` int auto_increment,
`incidentname` varchar(20),
`province` varchar(20),
`city` varchar(20),
`begin_time` datetime,
`end_time` datetime,
`influence` varchar(50),
`content` mediumtext,
`commituser` int,
`committime` datetime,
`ischeck` int,
primary key(`id`))engine=innodb default charset=utf8;
create table checkresult(
`incidentid` int,
`result` varchar(20),
`time` datetime,
`operator` int)engine=innodb default charset=utf8;


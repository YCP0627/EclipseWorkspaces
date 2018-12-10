set global time_zone = '+8:00';

create database java_experience;

create table student(
  `id` varchar(20) not null comment '学生学号',
  `name` varchar(5) not null comment '学生姓名',
  `sex` varchar(3) not null default '未知' comment '学生性别',
  `age` int(3) not null comment '年龄',
  `idCard` varchar(16) not null comment '学生身份证',
  `major` varchar(10) not null comment '专业',
  `className` varchar(10) not null comment '班级名称',
  primary key (id)
)engine = InnoDb default charset utf8MB4
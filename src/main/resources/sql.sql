-- set global time_zone = '+8:00';

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
)engine = InnoDb default charset utf8MB4;


create table class(
  `class_id` varchar(20) not null comment '课程编号号',
  `class_name` varchar(20) not null comment '课程名称',
  `class_hour` int not null comment '课时',
  `class_proprety` tinyint(1) not null comment '课程性质',
  `score` float not null comment '学分',
  `teacher` varchar(20) not null comment '授课老师',
  primary key(class_id)
)engine = InnoDb default  charset  utf8MB4;

create  table grade(
  `grade_id` int(11) AUTO_INCREMENT not null comment 'id',
  `id` varchar(20) not null comment '学生学号',
  `name` varchar(5) not null comment '学生姓名',
  `class_id` varchar(20) not null comment '课程编号',
  `class_name` varchar(20) not null comment '课程名称',
  `class_grade` float not null comment '分数',
  primary key (grade_id),
  foreign key (id) references student(id),
  foreign key (class_id) references class(class_id)
)engine =InnoDb default  charset utf8MB4;
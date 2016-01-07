create table admins (
  admin_id integer,
  name varchar2(30),
  password varchar2(60),
  type varchar(20),
  email varchar2(50)
);

create table users (
  user_id integer,
  email varchar2(50),
  password varchar2(60),
  firstname varchar2(30),
  lastname varchar2(30),
  website varchar2(50),
  description varchar2(100)
);

create table contests (
  contest_id integer,
  admin_id integer,
  user_id integer,
  name varchar2(50),
  start_date date,
  finish_date date,
  prize varchar(30),
  description varchar2(50)
);

create table files (
  file_id integer,
  user_id integer,
  contest_id integer,
  name varchar2(30),
  type varchar2(20),
  visible varchar2(20),
  description varchar2(100)
);

create table voters (
  voter_id integer,
  file_id integer,
  ip_address integer
);

create table reports (
  report_id integer,
  admin_id integer,
  file_id integer
);

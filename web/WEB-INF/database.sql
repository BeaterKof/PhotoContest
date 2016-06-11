create table admins (
  admin_id integer,
  name varchar2(30),
  password varchar2(60),
  type varchar(10),
  email varchar2(50)
);

create table users (
  user_id integer,
  email varchar2(50),
  password varchar2(60),
  first_name varchar2(30),
  last_name varchar2(30),
  website varchar2(50),
  description varchar2(100),
  status integer,
  type varchar2(10)
);

create table contests (
  contest_id integer,
  admin_id integer,
  name varchar2(50),
  start_date date,
  finish_date date,
  prize varchar(30),
  description varchar2(50),
  winner_id integer
);

create table files (
  file_id integer,
  user_id integer,
  contest_id integer,
  name varchar2(30),
  type varchar2(20),
  description varchar2(200),
  path varchar(200),
  date_added date
);

create table file_voter (
  file_voter_id integer,
  voter_id integer,
  file_id integer
);

create table voters (
  voter_id integer,
  ip_address varchar2(20)
);

create table reports (
  report_id integer,
  file_id integer,
  contest_id integer,
  reporter_email varchar2(50),
  message varchar2(100)
);

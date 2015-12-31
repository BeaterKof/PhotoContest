create table admins (
  admin_id integer,
  name varchar2(30),
  type number,
  password varchar2(30),
  email varchar2(30)
);

create table users (
  user_id integer,
  password varchar2(30),
  firstname varchar2(30),
  lastname varchar2(30),
  email varchar2(30),
  website varchar2(50),
  description varchar2(100)
);

create table contests (
  contest_id integer,
  admin_id integer,
  winner integer,
  name varchar2(30),
  description varchar2(50),
  prize varchar(30),
  start_date date,
  finish_date date
);

create table file (
  file_id integer,
  user_id integer,
  contest_id integer,
  description varchar2(30),
  visible number,
  name varchar2(30),
  type varchar2(30)
);


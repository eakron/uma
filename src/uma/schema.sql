drop table walks;
drop table teaches;
drop table registered;
drop table occasion;
drop table course;
drop table horse;
drop table uma_user;

create table uma_user (
  id serial primary key,
  firstName text,
  lastName text,
  personalIDNumber text,
  phoneNumbers text,
  email text,
  password text,
  username text,
  student boolean,
  streetAddress text,
  postalCode text,
  city text,
  notes text,
  guardians text,
  membershipNumber text);

create table horse (
  id serial primary key,
  name text,
  year text,
  height text,
  color text,
  description text);

create table course (
  id serial primary key,
  title text,
  difficulty text,
  semester text,
  weekday text,
  startTime timetz,
  endTime timetz,
  seats integer,
  price integer,
  notes text);

create table occasion (
  id serial primary key,
  date timestamp,
  description text,
  startTime timetz,
  endTime timetz,
  price integer,
  course integer references course(id),
  cancelees integer array);

create table registered (
  user_id integer references uma_user(id),
  course_id integer references course(id)
);

create table teaches (
  user_id integer references uma_user(id),
  course_id integer references course(id)
);

create table walks (
  horse_id integer references horse(id),
  occasion integer references occasion(id)
);
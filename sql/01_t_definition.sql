create table t_definition(
  id int not null,
  definicio varchar2(100) not null,
  subject varchar2(100) not null,
  registrydate date not null,
  description varchar2(355),
  constraint t_definition_pk primary key(id)
);

create sequence t_definition;

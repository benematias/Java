create table t_definition(
  id int not null,
  definicio varchar2(100) not null,
  subject varchar2(100) not null,
  registrydate number(4) not null,
  description varchar2(500),
  constraint t_definition_pk primary key(id)
);

create sequence s_definition;
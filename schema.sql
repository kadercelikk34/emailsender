create table email
(
   id integer not null,
   createdDate varchar(255) not null,
   account varchar(255) not null,
   endPointUrl varchar(255) not null,
   newsletter varchar(255) not null,
   emailType varchar(255) not null,
   primary key(id)
);
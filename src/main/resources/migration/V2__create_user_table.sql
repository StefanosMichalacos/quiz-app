create table "user" (
	id bigint,
	first_name varchar (40) not null,
	last_name varchar(40) not null,
	email varchar(60) not null,
	"password" varchar(20) not null,
	"user_role" bigint not null,
	foreign key ("user_role") references"description" (id),
	primary key (id)
);
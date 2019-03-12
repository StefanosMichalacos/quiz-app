create table "question" (
	id bigint,
	description text not null,
	user_level varchar(20) not null,
	primary key (id)
);
create table "quiz" (
	id bigint generated by default as identity,
	description text not null,
	primary key (id)
);
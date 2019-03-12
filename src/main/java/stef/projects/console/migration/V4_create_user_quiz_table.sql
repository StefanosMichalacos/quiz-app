create table "user_quiz" (
	id bigint,
	user_id bigint not null,
	quiz_id bigint not null,
	score float not null,
	foreign key (user_id) references "user" (id),
	foreign key (quiz_id) references "quiz" (id),
	primary key (id)
	
);
create table "quiz_question" (
	id bigint,
	quiz_id bigint not null,
	question_id bigint not null,
	foreign key (quiz_id) references "quiz" (id),
	foreign key (question_id) references "question" (id),
	primary key (id)
);
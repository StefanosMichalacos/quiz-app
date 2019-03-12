create table "question_choice" (
	id bigint,
	question_id bigint not null,
	choice_id bigint not null,
	correct_choice boolean not null,
	foreign key (question_id) references "question" (id),
	foreign key (choice_id) references "choice" (id),
	primary key (id)
);
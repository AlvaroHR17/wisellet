--
-- INITIAL SQL SCRIPT
--

create database expense;

create table users (
    id bigint unsigned auto_increment not null,
   	first_name varchar(255) not null,
   	last_name varchar(255) not null,
   	email varchar(255) not null,
   	roles varchar(255) not null,
    primary key(id)
);

alter table users add unique (email);

create table transactions (
    id bigint unsigned auto_increment not null,
    user_id bigint unsigned not null,
    creation_date timestamp not null,
    amount float not null,
    primary key(id)
);

alter table transactions add foreign key (user_id) references users(id);




create table income_types (
	id bigint unsigned auto_increment not null,
	description varchar(255) not null,
	user_id bigint unsigned not null,
	primary key(id)
);

alter table income_types add foreign key (user_id) references users(id);

create table incomes (
	id bigint unsigned auto_increment not null,
	income_type_id bigint unsigned not null,
	primary key(id)
);

alter table incomes add constraint incomes_transactions_fk foreign key (id) references transactions(id);
alter table incomes add foreign key (income_type_id) references income_types(id);




create table fixed_expense_types (
	id bigint unsigned auto_increment not null,
	description varchar(255) not null,
	user_id bigint unsigned not null,
	primary key(id)
);

alter table fixed_expense_types add foreign key (user_id) references users(id);

create table fixed_expenses (
	id bigint unsigned auto_increment not null,
	fixed_expenses_type_id bigint unsigned not null,
	primary key(id)
);

alter table fixed_expenses add constraint fixed_expenses_transactions_fk foreign key (id) references transactions(id);
alter table fixed_expenses add foreign key (fixed_expenses_type_id) references fixed_expense_types(id);




create table variable_expense_types (
	id bigint unsigned auto_increment not null,
	description varchar(255) not null,
	user_id bigint unsigned not null,
	primary key(id)
);

alter table variable_expense_types add foreign key (user_id) references users(id);

create table variable_expenses (
	id bigint unsigned auto_increment not null,
	variable_expenses_type_id bigint unsigned not null,
	primary key(id)
);

alter table variable_expenses add constraint variable_expenses_transactions_fk foreign key (id) references transactions(id);
alter table variable_expenses add foreign key (variable_expenses_type_id) references variable_expense_types(id);
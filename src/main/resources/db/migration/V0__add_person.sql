create table person(
    id bigint not null auto_increment,
    external_id varchar(120) not null,
    title varchar(120),
    forename varchar(120),
    surname varchar(120),
    constraint person_pk primary key (id),
    constraint person_external_id unique (external_id)
);

insert into person(external_id, title, forename, surname)
values ( 'system-user', null, 'system', 'user' );

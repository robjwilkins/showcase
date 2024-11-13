create table book
(
    id          bigint       not null auto_increment,
    external_id varchar(120) not null,
    title       varchar(120),
    author      varchar(120),
    constraint book_pk primary key (id),
    constraint book_external_id unique (external_id)
);

insert into book(external_id, title, author)
values ('hobbit_01', 'The Hobbit', 'JRR Tolkien');
insert into book(external_id, title, author)
values ('lord_01', 'Fellowship of the Ring', 'JRR Tolkien');
insert into book(external_id, title, author)
values ('lord_02', 'The Two Towers', 'JRR Tolkien');
insert into book(external_id, title, author)
values ('lord_03', 'Return of the King', 'JRR Tolkien');
insert into book(external_id, title, author)
values ('madding_01', 'Far from the Madding Crowd', 'Thomas Hardy');
insert into book(external_id, title, author)
values ('merchant_01', 'The Merchant of Venice', 'William Shakespear');
insert into book(external_id, title, author)
values ('hamlet_01', 'Hamlet', 'William Shakespear');
insert into book(external_id, title, author)
values ('romeo_01', 'Romeo and Juliet', 'William Shakespear');
insert into book(external_id, title, author)
values ('macbeth_01', 'Macbeth', 'William Shakespear');
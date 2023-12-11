create table users
(
    id          bigint       not null auto_increment,
    external_id varchar(120) not null,
    name        varchar(120),
    created_at  timestamp    not null default (current_timestamp)
);
create table if not exists book
(
    id       int4 not null,
    author   varchar(35),
    image varchar(255),
    quantity int4,
    title    varchar(35),
    primary key (id)
);
create table users (
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    email varchar(40) unique,
    login varchar(25) unique,
    password varchar,
    role varchar(10)
);

create table events (
    id serial primary key,
    name varchar(75) unique,
    description varchar,
    capacity int,
    host varchar(25),
    active boolean,
    place varchar,
    time_start varchar(16),
    time_end varchar(16)
);

create table created_events (
    userid bigint references users(id) on delete restrict,
    eventid bigint references events(id) on delete restrict
);

create table subscribed_events (
    userid bigint references users(id) on delete restrict,
    eventid bigint references events(id) on delete restrict
);

create table participants (
    eventid bigint references events(id) on delete restrict,
    userid bigint references users(id) on delete restrict
);


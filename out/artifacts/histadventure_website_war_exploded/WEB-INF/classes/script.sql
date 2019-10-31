create table users (
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    email varchar(40) unique,
    login varchar(25) unique,
    password varchar(20),
    role varchar(10),
    subscribed_events varchar[]
);

create table events (
    id serial primary key,
    name varchar(75) unique,
    description varchar,
    participants varchar[],
    capacity int,
    host varchar(25),
    active boolean,
    place varchar,
    time_start varchar(16),
    time_end varchar(16),
    subscribed_users varchar[]
);
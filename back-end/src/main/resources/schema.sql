drop table if exists options;
drop table if exists locations;
drop table if exists appointments;
drop table if exists posts;
drop table if exists mycars;
drop table if exists users;
drop table if exists car_options;
drop table if exists cars;
drop table if exists entire_options;

-- Create the cars table
create table cars
(
    id            bigint auto_increment primary key,
    car_name      varchar(100) not null,
    car_kor_name  varchar(100) not null,
    car_image_url varchar(255) not null
);

-- Create the entire_options table
create table entire_options
(
    id            bigint auto_increment primary key,
    option_name   varchar(100) not null,
    category_name varchar(100) not null
);

-- Create the car_options table
create table car_options
(
    car_id           bigint not null,
    entire_option_id bigint not null,
    primary key (car_id, entire_option_id),
    foreign key (car_id) references cars (id) on update cascade on delete cascade,
    foreign key (entire_option_id) references entire_options (id) on update cascade on delete cascade
);

-- Create the users table
create table users
(
    id            bigint auto_increment primary key,
    email         varchar(255) not null unique,
    user_name     varchar(255) not null,
    phone_number  varchar(100) not null,
    user_password varchar(255) null,
    created_at    date         not null
);

-- Create the mycars table
create table mycars
(
    id      bigint auto_increment primary key,
    user_id bigint null,
    car_id  bigint not null,
    foreign key (user_id) references users (id) on update cascade on delete cascade,
    foreign key (car_id) references cars (id) on update cascade on delete cascade
);

-- Create the options table
create table options
(
    id       bigint auto_increment primary key,
    name     varchar(255) not null,
    category varchar(255) not null,
    mycar_id bigint       not null,
    foreign key (mycar_id) references mycars (id) on update cascade on delete cascade
);

-- Create the posts table
create table posts
(
    id          bigint auto_increment primary key,
    ride_option varchar(100) not null,
    mycar_id    bigint       not null,
    requirement text         null,
    foreign key (mycar_id) references mycars (id) on update cascade on delete cascade
);

-- Create the appointments table
create table appointments
(
    id        bigint auto_increment primary key,
    date      date         not null,
    post_id   bigint       not null,
    tester_id bigint       null,
    status    varchar(100) not null,
    foreign key (post_id) references posts (id) on update cascade on delete cascade,
    foreign key (tester_id) references users (id) on update cascade on delete cascade
);

-- Create the locations table
create table locations
(
    id        bigint auto_increment primary key,
    longitude varchar(255) not null,
    latitude  varchar(255) not null,
    post_id   bigint       not null,
    foreign key (post_id) references posts (id) on update cascade on delete cascade
);

-- Create the index on latitude and longitude in the locations table
create index latitude_longitude_index on locations (latitude, longitude);

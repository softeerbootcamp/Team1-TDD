drop table if exists options;
drop table if exists locations;
drop table if exists appointments;
drop table if exists posts;
drop table if exists users;
drop table if exists caroptions;
drop table if exists cars;
drop table if exists entireoptions;

create table users
(
    id            bigint auto_increment
        primary key,
    email         varchar(255) not null UNIQUE,
    user_name     varchar(255) not null,
    phone_number  varchar(100) not null,
    user_password varchar(255) null
);

create table posts
(
    id          bigint auto_increment
        primary key,
    ride_option varchar(100) not null,
    car_name    varchar(100) not null,
    user_id     bigint       not null,
    requirement text         null,
    constraint posts_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
);

create table appointments
(
    id        bigint auto_increment
        primary key,
    date      date         not null,
    post_id   bigint       not null,
    tester_id bigint       null,
    status    varchar(100) not null,
    constraint appointments_posts_id_fk
        foreign key (post_id) references posts (id)
            on update cascade on delete cascade,
    constraint appointments_users_id_fk
        foreign key (tester_id) references users (id)
            on update cascade on delete cascade
);

create table locations
(
    longitude varchar(255) not null,
    latitude  varchar(255) not null,
    post_id   bigint       not null,
    id        bigint auto_increment
        primary key,
    constraint locations_posts_id_fk
        foreign key (post_id) references posts (id)
            on update cascade on delete cascade
);

create table options
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    category varchar(255) not null,
    post_id  bigint       not null,
    constraint options_posts_id_fk
        foreign key (post_id) references posts (id)
            on update cascade on delete cascade
);

create table cars
(
    id       bigint auto_increment primary key,
    car_name varchar(100) not null
);

create table entireoptions
(
    id            bigint auto_increment primary key,
    option_name   varchar(100) not null,
    category_name varchar(100) not null
);

create table caroptions
(
    id        bigint auto_increment primary key,
    car_id    bigint not null,
    entireoption_id bigint not null,
    constraint caroptions_cars_id_fk
        foreign key (car_id) references cars (id) on update cascade on delete cascade,
    constraint caroptions_entireoptions_id_fk
        foreign key (entireoption_id) references entireoptions (id) on update cascade on delete cascade
);

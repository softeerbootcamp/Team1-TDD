drop table if exists options;
drop table if exists locations;
drop table if exists appointments;
drop table if exists posts;
drop table if exists users;

create table users
(
    id            bigint auto_increment
        primary key,
    user_name     varchar(255) not null,
    phone_number  varchar(100) not null,
    user_password varchar(255) null
);

create table posts
(
    id           bigint auto_increment
        primary key,
    ride_option  varchar(100) not null,
    drive_career varchar(100) not null,
    car_name     varchar(100) not null,
    user_id      bigint       not null,
    requirement  text null,
    constraint posts_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
);

create table appointments
(
    id        bigint auto_increment
        primary key,
    date      datetime     not null,
    post_id   bigint       not null,
    tester_id bigint null,
    status    varchar(100) not null,
    constraint appointments_posts_id_fk
        foreign key (post_id) references posts (id),
    constraint appointments_users_id_fk
        foreign key (tester_id) references users (id)
            on update cascade on delete cascade
);

create table locations
(
    location_x varchar(255) not null,
    location_y varchar(255) not null,
    post_id    bigint       not null,
    id         bigint auto_increment
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

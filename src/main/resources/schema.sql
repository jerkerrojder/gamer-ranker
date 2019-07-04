create table if not exists games
(
    id       identity not null,
    gamename varchar(50) unique default null,
    primary key (id)
);

create table if not exists users
(
    id       identity    not null,
    username varchar(50) unique not null,
    primary key (id)
);

create table if not exists matches
(
    id         identity not null,
    gamesid    bigint  default null,
    user1id   bigint  default null,
    user2id   bigint  default null,
    scoreuser1 int(11) default null,
    scoreuser2 int(11) default null,
);

CREATE TABLE points
(
    gamesid       bigint not null,
    usersid       bigint not null,
    player_points double default null,
    primary key (gamesid, usersid),
);


create unique index points_index on points (player_points);

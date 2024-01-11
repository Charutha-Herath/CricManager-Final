create table donation
(
    DId    varchar(5)   not null
        primary key,
    type   varchar(45)  null,
    value  double       null,
    `desc` varchar(100) null,
    Date   varchar(10)  null
);

create table user
(
    UId       varchar(5)  not null
        primary key,
    UName     varchar(45) not null,
    Pwd       varchar(45) null,
    StartDate date        null
);

create table coach
(
    CId  varchar(5)  not null
        primary key,
    CUId varchar(5)  null,
    name varchar(45) null,
    type varchar(45) null,
    constraint CUId
        foreign key (CUId) references user (UId)
            on update cascade on delete cascade
);

create table matches
(
    MId           varchar(5)  not null
        primary key,
    MUId          varchar(5)  null,
    ground        varchar(45) null,
    opposing_team varchar(45) null,
    est_cost      double      null,
    balance       double      null,
    Date          varchar(12) null,
    Status        varchar(15) null,
    constraint MUId
        foreign key (MUId) references user (UId)
            on update cascade on delete cascade
);

create table cost
(
    CostId         varchar(5) not null
        primary key,
    CostMId        varchar(5) null,
    ground_fee     double     null,
    umpire_fee     double     null,
    equipment_fee  double     null,
    meal_and_other double     null,
    total          double     null,
    constraint CostMId
        foreign key (CostMId) references matches (MId)
            on update cascade on delete cascade
);

create index CostMId_idx
    on cost (CostMId);

create index MUId_idx
    on matches (MUId);

create table player
(
    PId  varchar(5)  not null
        primary key,
    PUId varchar(5)  null,
    name varchar(45) null,
    age  int         null,
    type varchar(45) null,
    constraint PUId
        foreign key (PUId) references user (UId)
            on update cascade on delete cascade
);

create index PUId_idx
    on player (PUId);

create table player_coach
(
    CId varchar(5) null,
    PId varchar(5) null,
    constraint CId
        foreign key (CId) references coach (CId)
            on update cascade on delete cascade,
    constraint PId
        foreign key (PId) references player (PId)
            on update cascade on delete cascade
);

create index CId_idx
    on player_coach (CId);

create index PId_idx
    on player_coach (PId);

create table sponsor
(
    SId     varchar(5)  not null
        primary key,
    UId     varchar(5)  null,
    name    varchar(45) null,
    company varchar(45) null,
    value   double      null,
    Date    varchar(10) null,
    constraint UId
        foreign key (UId) references user (UId)
            on update cascade on delete cascade
);

create index UId_idx
    on sponsor (UId);

create table umpire
(
    UmpId  varchar(5)  not null
        primary key,
    UmpUId varchar(5)  null,
    name   varchar(45) null,
    constraint UmpUId
        foreign key (UmpUId) references user (UId)
            on update cascade on delete cascade
);

create table ump_match
(
    UmpId varchar(5) null,
    MId   varchar(5) null,
    constraint MId
        foreign key (MId) references matches (MId),
    constraint UmpId
        foreign key (UmpId) references umpire (UmpId)
);

create index MId_idx
    on ump_match (MId);

create index UmpId_idx
    on ump_match (UmpId);

create index UmpUId_idx
    on umpire (UmpUId);


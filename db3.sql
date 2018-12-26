create table chronicle
(
  chronicleId   int auto_increment,
  chronicleName varchar(20) not null,
  constraint chronicle_chronicleId_uindex
  unique (chronicleId)
);

alter table chronicle
  add primary key (chronicleId);

create table mark
(
  mark     int null,
  userID   int null,
  serverId int null
);

create index fk_mark_server1_idx
  on mark (serverId);

create index fk_mark_userId_idx
  on mark (userID);

create table review
(
  userID     int          not null,
  serverId   int          not null,
  reviewText varchar(100) not null,
  reviewDate date         not null
);

create index fk_review__userID_idx
  on review (userID);

create index fk_review_server1_idx
  on review (serverId);

create table role
(
  roleID   int auto_increment,
  roleName varchar(20) not null,
  constraint role_roleID_uindex
  unique (roleID)
);

alter table role
  add primary key (roleID);

create table server
(
  serverId          int auto_increment,
  chronicleId       int         not null,
  serverName        varchar(20) not null,
  serverDescription varchar(70) not null,
  constraint server_serverId_uindex
  unique (serverId)
);

create index fk_server_chronicle1_idx
  on server (chronicleId);

alter table server
  add primary key (serverId);

create table user
(
  userID   int auto_increment,
  roleID   int         not null,
  login    varchar(20) not null,
  mail     varchar(20) not null,
  password varchar(20) not null,
  status   int         not null,
  constraint user_login_uindex
  unique (login),
  constraint user_userID_uindex
  unique (userID),
  constraint fk_user_role
  foreign key (roleID) references role (roleID)
);

create index fk_user_role_idx
  on user (roleID);

alter table user
  add primary key (userID);



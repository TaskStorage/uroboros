# CREATE SCHEMA IF NOT EXISTS `springstorage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
# Use `springstorage`;

create table hibernate_sequence (next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 2 );

create table Task (
  id bigint not null,
  content varchar(2048) not null,
  description varchar(2048) not null,
  filename varchar(255),
  user_id bigint,
  primary key (id)
) engine=InnoDB;


create table User (
  id bigint not null,
  activationCode varchar(255),
  active bit not null,
  email varchar(255),
  password varchar(255) not null,
  username varchar(255) not null,
  primary key (id)
) engine=InnoDB;


create table user_role (
  user_id bigint not null,
  roles varchar(255)
) engine=InnoDB;

create table persistent_logins(
  username varchar(255) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
);

alter table Task add constraint task_user_fk
foreign key (user_id) references User (id);


alter table user_role add constraint user_role_user_fk
foreign key (user_id) references User (id);

insert into User (id, username, password, active)
values (1, 'admin', '$2a$08$kKarlr4.rWGMRl62.51PeuDx0L0uCnsTeKz5sK7IWrtlOGy/VdNVS', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');

insert into User (id, username, password, active)
values (2, 'user', '$2a$08$kKarlr4.rWGMRl62.51PeuDx0L0uCnsTeKz5sK7IWrtlOGy/VdNVS', true);

insert into user_role (user_id, roles)
values (2, 'USER'), (2, 'ADMIN');
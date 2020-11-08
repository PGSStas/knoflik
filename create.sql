create table rooms (id varchar(32) not null, admin_id varchar(16), pack_id varchar(32), primary key (id));
create table users (id varchar(32) not null, username varchar(16) not null, primary key (id))

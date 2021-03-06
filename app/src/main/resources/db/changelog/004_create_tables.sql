create table person (id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), creation_time timestamp, primary key (id));
create table person_aud (id bigint not null, rev integer not null, revtype tinyint, first_name varchar(255), last_name varchar(255), creation_time timestamp, primary key (id, rev));
create table revinfo (rev integer generated by default as identity, revtstmp bigint, primary key (rev));
alter table person_aud add constraint FK9lyxk62ui3cyr5k0w8etnfqkm foreign key (rev) references revinfo;
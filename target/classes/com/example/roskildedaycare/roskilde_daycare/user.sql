create table user
(
	ID int auto_increment
		primary key,
	firstName varchar(32) null,
	lastName varchar(32) null,
	telephoneNumber int null,
	userName varchar(32) null,
	password varchar(32) null,
	admin tinyint(1) null
);


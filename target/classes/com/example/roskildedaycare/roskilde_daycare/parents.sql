create table parents
(
	ID int auto_increment
		primary key,
	firstName varchar(32) null,
	lastName varchar(32) null,
	telephoneNumber int null,
    students_id int not null,
	CONSTRAINT fk_students
	    FOREIGN KEY (students_id) REFERENCES roskilde_daycare.students(ID)
);


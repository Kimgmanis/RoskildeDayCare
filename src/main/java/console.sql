SELECT TOP (ID) FROM roskilde_daycare.students;

SELECT ID FROM roskilde_daycare.students ORDER BY ID DESC LIMIT 1;

REPLACE INTO roskilde_daycare.schedule (dateSchedule, studentGroup, teacher) VALUES ('2022-03-21','A','Kim');

SELECT studentGroup, dateSchedule, teacher FROM roskilde_daycare.schedule LIMIT 2,1;

SELECT ID FROM roskilde_daycare.parents
WHERE students_id = 1 LIMIT 1,1 ;

SELECT password, firstName, lastName, admin, telephoneNumber FROM roskilde_daycare.user WHERE ID = 1

SELECT ID FROM roskilde_daycare.parents WHERE students_id = 1 LIMIT 0,1;

create table schedule
(
    studentGroup varchar(32) NOT NULL ,
    dateSchedule date NOT NULL ,
    teacher varchar(32) NOT NULL ,
    constraint schedule_pk
        primary key (studentGroup, dateSchedule)
);
SET foreign_key_checks = 0;
REPLACE INTO students (ID, firstName, lastName, studentGroup) VALUES (1, 'Klay', 'Spark', 'B');
SET foreign_key_checks =1;
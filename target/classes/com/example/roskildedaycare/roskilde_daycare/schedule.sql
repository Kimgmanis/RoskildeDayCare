create table schedule
(
    studentGroup varchar(32) NOT NULL ,
    dateSchedule date NOT NULL ,
    teacher varchar(32) NOT NULL ,
    constraint schedule_pk
        primary key (studentGroup, dateSchedule)
);
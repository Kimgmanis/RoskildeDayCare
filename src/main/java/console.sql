SELECT TOP (ID) FROM roskilde_daycare.students;

SELECT ID FROM roskilde_daycare.students ORDER BY ID DESC LIMIT 1;

REPLACE INTO roskilde_daycare.schedule (dateSchedule, studentGroup, teacher) VALUES ('2022-03-21','A','Kim');

SELECT studentGroup, dateSchedule, teacher FROM roskilde_daycare.schedule LIMIT 2,1;
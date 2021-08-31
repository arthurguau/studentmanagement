
Drop database if exists studentmanagement;
create database studentmanagement;

USE studentmanagement;

CREATE TABLE if not exists student (
    student_id INT auto_increment PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    contact VARCHAR(50), 
    course VARCHAR(50) NOT NULL
 );

insert into student (first_name, last_name, contact, course) VALUES ( 'Arthur', 'Gu', 'arthur@hotmail.com', 'CS');
insert into student (first_name, last_name, contact, course) VALUES ( 'Tom', 'Zhange', 'tom@hotmail.com', 'CS');
 
CREATE USER 'tester'@'localhost' IDENTIFIED BY 'tester';

GRANT ALL PRIVILEGES ON studentmanagement.* TO 'tester'@'%';

FLUSH PRIVILEGES;
 
 
 
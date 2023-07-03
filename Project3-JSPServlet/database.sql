-- Database: StudentManagement

-- DROP DATABASE IF EXISTS "StudentManagement";

CREATE DATABASE studentmanagement;

CREATE TABLE student (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  grade FLOAT,
  birthday DATE,
  address VARCHAR(100),
  note VARCHAR(100)
);

CREATE TABLE course (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  lecture VARCHAR(50),
  year INT,
  note VARCHAR(100)
);

CREATE TABLE enrollment (
  student_id INT NOT NULL,
  course_id INT NOT NULL,
  grade FLOAT,
  PRIMARY KEY (student_id, course_id),
  FOREIGN KEY (student_id) REFERENCES student(id),
  FOREIGN KEY (course_id) REFERENCES course(id)
);

insert into student(name, grade, birthday, address, note) values("Nguyen Van E",5.9,"2002-03-04","HCM", "...");
insert into student(name, grade, birthday, address, note) values("Tran Van F",4.9,"2002-02-07","HN", "");
insert into student(name, grade, birthday, address, note) values("Pham Van H",5.9,"2002-03-06","HCM", "");
insert into student(name, grade, birthday, address, note) values("Le Van N",5.9,"2002-07-05","HCM", "");
insert into student(name, grade, birthday, address, note) values("Bui Thi I",5.9,"2002-02-05","HCM", "");
insert into student(name, grade, birthday, address, note) values("Dang Van E",5.9,"2002-06-04","BD", "");
insert into student(name, grade, birthday, address, note) values("Do Van A",5.9,"2002-03-09","BT", "");
insert into student(name, grade, birthday, address, note) values("Le Van V",5.9,"2002-03-04","BRVT", "");


insert into course(name, lecture, year, note) values ("Applied to Big Data", "Spark Fundamentals", 2023, "optional");
insert into course(name, lecture, year, note) values ("Introduction to Big Data", "Hadoop Fundamentals", 2023, "optional");
insert into course(name, lecture, year, note) values ("Introduction to Programming", "Pointer", 2023, "require");
insert into course(name, lecture, year, note) values ("Data Structure and Algorithms", "Binary Tree", 2023, "optional");
insert into course(name, lecture, year, note) values ("Advanced Programming", "Linked List", 2023, "optional");

insert into enrollment(student_id, course_id, grade) values (1,1,4.5);
insert into enrollment(student_id, course_id, grade) values (1,2,5.5);
insert into enrollment(student_id, course_id, grade) values (2,1,4.8);
insert into enrollment(student_id, course_id, grade) values (2,3,7.8);
insert into enrollment(student_id, course_id, grade) values (4,4,6.9);
insert into enrollment(student_id, course_id, grade) values (4,5,8.0);
insert into enrollment(student_id, course_id, grade) values (3,1,2.8);









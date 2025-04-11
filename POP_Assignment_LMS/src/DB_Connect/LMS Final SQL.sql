CREATE DATABASE IF NOT EXISTS library_db;
use library_db;


create table student_login (
	user_name varchar(50) not null,
    password varchar(50) not null
);

insert into student_login (user_name, password) values 
("Nabin", "nabin@123"),
("Melon", "melon@123"),
("Suren", "suren@123");

select * from student_login;





create table librarian_login (
	user_name varchar(50) not null,
    password varchar(50) not null
);

insert into librarian_login (user_name, password) values 
("pcps", "pcpscollege");

select * from librarian_login;





CREATE TABLE students (
    student_id INT PRIMARY KEY ,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    level VARCHAR(50),
    address VARCHAR(50)
);

insert into students (student_id, fullname, email, phone, level, address) values 
(1, 'Nabin Dhakal', 'nabin@gmail.com', '9874125361', 'L4', 'satdobato'),
(2, 'Melan Parajuli', 'melan@gmail.com', '9823156403', 'L4', 'bagbazar'),
(3, 'Shulabh Shah', 'shulabh@gmail.com', '9800154231', 'L6', 'setopool'),
(4, 'Suren Thapa', 'suren@gmail.com', '9710254316', 'L5', 'dhobikhola'),
(5, 'Upal Karki', 'upal@gmail.com', '9712450315', 'L6', 'balkumari');

select * from students;


CREATE TABLE IF NOT EXISTS book (
    book_ID INT PRIMARY KEY,
    Title VARCHAR(50) NOT NULL,
    Author VARCHAR(50) NOT NULL,
    Edition date, 
    Quantity INT
);


INSERT INTO book (book_ID, Title, Author, Edition, Quantity) VALUES
(11, 'Principle of Programming', 'Melan Parajuli', '2015-05-12', 3),
(12, 'Database', 'Nabin Dhakal', '2022-03-21', 4),
(13, 'Absolute Java', 'Rohit Thapa', '2024-11-01', 2),
(14, 'Networking Principle', 'Sulabh Shah', '2023-11-01', 4),
(15, 'Science', 'Ayush Sharma', '2020-01-11', 1);

select * from book;





create table borrow_book (
	trans_id int primary key,
	student_id int,
    book_ID int,
    borrowed_date datetime not null,
	foreign key (student_id) references students (student_id),
    foreign key (book_ID) references book (book_ID)
);

INSERT INTO borrow_book (trans_id, student_id, book_ID, borrowed_date) VALUES
(101, 3, 12, '2025-03-02'),
(102, 2, 11, '2025-03-12'),
(103, 1, 15, '2025-03-01'),
(104, 5, 13, '2025-03-14'),
(105, 4, 14, '2025-04-02');

select * from borrow_book;
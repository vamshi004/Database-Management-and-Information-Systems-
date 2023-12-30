CREATE TABLE book(
       book_id int,
       title varchar(255),
       category varchar(255),
       author varchar(255),
       PRIMARY KEY (book_id)
);

CREATE TABLE student(
      student_id int,
      name varchar(255),
      dept_name varchar(255),
      year varchar(255),
      semester varchar(255),
      PRIMARY KEY (student_id)
);

CREATE TABLE issue (
       student_id int, 
       book_id int,
       issue_date DATE,
       return_date DATE
);

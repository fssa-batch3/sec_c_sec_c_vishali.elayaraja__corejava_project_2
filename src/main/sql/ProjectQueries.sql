CREATE TABLE students (
    id INT AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    dob DATE NOT NULL,
    mobile_no BIGINT  NOT NULL UNIQUE KEY,
    email VARCHAR(100) NOT NULL UNIQUE KEY,
    password VARCHAR(100) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status TINYINT DEFAULT 1,
    PRIMARY KEY(id),
    CONSTRAINT email_id_chk CHECK (email LIKE '%_@__%.__%'),
    CONSTRAINT gender_chk CHECK (gender IN ('male', 'female', 'others'))
);


CREATE INDEX student_ind_fk ON students (email, first_name);

CREATE TABLE departments (
    id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    UNIQUE KEY (dept_name),
     CONSTRAINT dept_name_chk CHECK (dept_name IN ('CSE','ECE', 'EEE', 'MECH','IT'))
);


select * from departments;
select * from students;
CREATE INDEX departments_ind_fk ON departments (dept_name);

CREATE TABLE student_class (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    department_id INT NOT NULL,
    status BOOLEAN NOT NULL DEFAULT 1,
    FOREIGN KEY (department_id) REFERENCES departments(id) on delete cascade,
    FOREIGN KEY (student_id) REFERENCES students(id) on delete cascade
);

CREATE INDEX student_class_ind_fk ON student_class (student_id, department_id);

-- DESCRIBE QUERY --

DESC students;
DESC student_class;
DESC departments;


-- INSERT QUERY --

INSERT INTO students (first_name, last_name, gender, dob, mobile_no, email, password)
VALUES ('vishali', 'elayaraja', 'female', '2004-10-30', 8778972632, 'vishali@gmail.com', 'password'),
       ('pranaw', 'murugesan', 'male', '2004-10-30', 1234567890, 'pranaw@gmail.com', 'password1234');

INSERT INTO departments(id,dept_name) VALUES (101,'CSE');
INSERT INTO departments(id,dept_name) VALUES (102,'ECE');

INSERT INTO student_class(student_id, department_id) VALUES (1, 101);

INSERT INTO student_class(student_id, department_id) VALUES (1, 102);

-- SELECT QUERY --

SELECT * FROM student_class;
SELECT * FROM students;

SELECT * FROM departments;

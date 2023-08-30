USE vishali_elayaraja_corejava_project;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    roll_no INT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    dob DATE NOT NULL,
    mobile_no BIGINT  NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status TINYINT DEFAULT 1,
    UNIQUE KEY (email,mobile_no,roll_no),
    CONSTRAINT email_id_chk CHECK (email LIKE '%_@__%.__%'),
    CONSTRAINT gender_chk CHECK (gender IN ('male', 'female', 'others'))
);

CREATE INDEX student_ind_fk ON students (email, first_name);

CREATE TABLE departments (
    id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    UNIQUE KEY (dept_name)
);

//insert

CREATE INDEX departments_ind_fk ON departments (dept_name);

CREATE TABLE student_class (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    department_id INT NOT NULL,
    status BOOLEAN NOT NULL DEFAULT 1,
    FOREIGN KEY (department_id) REFERENCES departments(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
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

INSERT INTO departments(dept_name) VALUES ('BIOTECH');

INSERT INTO student_class(student_id, department_id) VALUES (1, 1);

-- SELECT QUERY --

SELECT * FROM student_class;
SELECT * FROM students;
SELECT * FROM departments;

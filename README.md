# College Admission App

## Description

The College Admission App makes it easy for students to apply to college and let College can manage applications efficiently, access applicant data, and communicate effectively with students. 

## DB TABLES

## students Table

| Column           | Type         | Null | Key | Default           | Extra                       |
|------------------|--------------|------|-----|-------------------|-----------------------------|
| id               | int          | NO   | PRI | auto_increment    |                             |
| first_name       | varchar(100) | NO   |     |                   |                             |
| last_name        | varchar(100) | NO   |     |                   |                             |
| gender           | varchar(10)  | NO   |     |                   | CHECK ('Male', 'Female', 'Others') |
| dob              | date         | YES  |     |                   |                             |
| mobile_no        | bigint       | NO   |     |                   |                             |
| email            | varchar(100) | NO   | UNI |                   | CONSTRAINT email_id_chk CHECK (email LIKE '%_@__%.__%')                    |
| password         | varchar(100) | NO   |     |                   |                             |
| created_date     | timestamp    |      |     | current_timestamp |                             |
| status           | tinyint      |      |     | 1                 |                             |


## departments Table

| Column  | Type         | Null | Key  | Default           | Extra          |
|---------|--------------|------|------|-------------------|----------------|
| id      | int          |      | PRI  |    |                |
| name    | varchar(100) | NO   | UNI  |                   |     CONSTRAINT dept_name_chk CHECK (dept_name IN ('CSE','ECE', 'EEE', 'MECH','IT'))       |


## student_class Table

| Column         | Type         | Null | Key | Default      | Extra          |
|----------------|--------------|------|-----|--------------|----------------|
| id             | int          | NO   | PRI |auto_increment|                |
| student_id     | int          | NO   | MUL |              |                |
| department_id  | int          | NO   | MUL |              |                |
| is_active      | boolean      | NO   |     | 1            |                |

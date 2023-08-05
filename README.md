# College Admission App

## Description

The College Admission App makes it easy for students to apply to colleges and let Colleges can manage applications efficiently, access applicant data, and communicate effectively with students.

## DB TABLES

## Students Table

| Column           | Type         | Null | Key | Default           | Extra                       |
|------------------|--------------|------|-----|-------------------|-----------------------------|
| id               | int          | NO   | PRI | auto_increment    |                             |
| first_name       | varchar(100) | NO   |     |                   |                             |
| last_name        | varchar(100) | NO   |     |                   |                             |
| gender           | varchar(10)  | NO   |     |                   | CHECK ('Male', 'Female', 'Others') |
| dob              | date         | YES  |     |                   |                             |
| mobile_no        | bigint       | NO   |     |                   |                             |
| email            | varchar(100) | NO   | UNI |                   |                             |
| password         | varchar(100) | NO   |     |                   |                             |
| created_date     | timestamp    |      |     | current_timestamp |                             |
| status           | tinyint      |      |     | 1                 |                             |


## Departments Table

| Column  | Type         | Null | Key  | Default           | Extra          |
|---------|--------------|------|------|-------------------|----------------|
| id      | int          |      | PRI  | auto_increment    |                |
| name    | varchar(100) | NO   | UNI  |                   |                |


## Student_Class Table

| Column         | Type         | Null | Key | Default      | Extra          |
|----------------|--------------|------|-----|--------------|----------------|
| id             | int          | NO   | PRI |auto_increment|                |
| student_id     | int          | NO   | MUL |              |                |
| department_id  | int          | NO   | MUL |              |                |
| is_active      | boolean      | NO   |     | 1            |                |

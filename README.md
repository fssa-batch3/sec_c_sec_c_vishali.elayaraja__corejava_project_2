# sec_c_sec_c_vishali.elayaraja__corejava_project_2



| Column      | Type           | Constraints                                 |
|-------------|----------------|---------------------------------------------|
| id          | int            | PRIMARY KEY, AUTO_INCREMENT                 |
| first_name  | varchar(100)   | NOT NULL                                    |
| last_name   | varchar(100)   | NOT NULL                                    |
| gender      | varchar(10)    | NOT NULL, CHECK ('Male', 'Female', 'Others')|
| dob         | date           |                                             |
| mobile_no   | bigint         | NOT NULL                                    |
| email       | varchar(100)   | NOT NULL, UNIQUE, CHECK (LIKE '%_@__%.__%') |
| password    | varchar(100)   | NOT NULL                                    |
| created_date| timestamp      | DEFAULT CURRENT_TIMESTAMP                   |
| status      | tinyint        | DEFAULT 1                                   |

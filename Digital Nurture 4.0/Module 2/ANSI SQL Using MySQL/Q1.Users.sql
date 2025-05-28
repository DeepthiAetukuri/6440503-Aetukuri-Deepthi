use javadb;
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique user identifier',
    full_name VARCHAR(100) NOT NULL COMMENT 'User’s full name',
    email VARCHAR(100) UNIQUE NOT NULL COMMENT 'User’s email',
    city VARCHAR(100) NOT NULL COMMENT 'City of the user',
    registration_date DATE NOT NULL COMMENT 'Date when user registered'
);

SELECT 
    COLUMN_NAME AS 'Column Name',
    COLUMN_TYPE AS 'Data Type',
    CONCAT(
        IF(IS_NULLABLE = 'NO', 'NOT NULL ', ''),
        IF(COLUMN_KEY = 'PRI', 'PRIMARY KEY ', ''),
        IF(COLUMN_KEY = 'UNI', 'UNIQUE ', ''),
        IF(EXTRA = 'auto_increment', 'AUTO_INCREMENT', '')
    ) AS 'Constraints',
    COLUMN_COMMENT AS 'Description'
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'javadb'
  AND TABLE_NAME = 'User';

INSERT INTO User (user_id, full_name, email, city, registration_date) VALUES
(1, 'Alice Johnson', 'alice@example.com', 'New York', '2024-12-01'),
(2, 'Bob Smith', 'bob@example.com', 'Los Angeles', '2024-12-05'),
(3, 'Charlie Lee', 'charlie@example.com', 'Chicago', '2024-12-10'),
(4, 'Diana King', 'diana@example.com', 'New York', '2025-01-15'),
(5, 'Ethan Hunt', 'ethan@example.com', 'Los Angeles', '2025-02-01');

select * from User;



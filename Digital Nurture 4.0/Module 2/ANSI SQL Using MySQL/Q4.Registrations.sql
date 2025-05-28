use javadb;
CREATE TABLE Registrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique registration ID',
    user_id INT COMMENT 'User who registered',
    event_id INT COMMENT 'Registered event',
    registration_date DATE NOT NULL COMMENT 'Date of registration'
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

SELECT 
    COLUMN_NAME AS 'Column Name',
    COLUMN_TYPE AS 'Data Type',
    TRIM(
        CONCAT(
            IF(IS_NULLABLE = 'NO', 'NOT NULL ', ''),
            IF(COLUMN_KEY = 'PRI', 'PRIMARY KEY ', ''),
            IF(COLUMN_KEY = 'UNI', 'UNIQUE ', ''),
            IF(EXTRA = 'auto_increment', 'AUTO_INCREMENT', '')
        )
    ) AS 'Constraints',
    COLUMN_COMMENT AS 'Description'
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'javadb' 
  AND TABLE_NAME = 'Registrations';

INSERT INTO Registrations (
    registration_id, user_id, event_id, registration_date
) VALUES
(1, 1, 1, '2025-05-01'),
(2, 2, 1, '2025-05-02'),
(3, 3, 2, '2025-04-30'),
(4, 4, 2, '2025-04-28'),
(5, 5, 3, '2025-06-15');


SELECT event_id FROM Events;


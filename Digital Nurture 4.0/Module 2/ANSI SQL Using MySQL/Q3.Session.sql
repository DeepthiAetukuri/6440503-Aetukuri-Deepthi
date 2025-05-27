use javadb;
CREATE TABLE Sessions (
    session_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique session identifier',
    event_id INT COMMENT 'Associated event',
    title VARCHAR(200) NOT NULL COMMENT 'Title of the session',
    speaker_name VARCHAR(100) NOT NULL COMMENT 'Name of speaker',
    start_time DATETIME NOT NULL COMMENT 'Session start time',
    end_time DATETIME NOT NULL COMMENT 'Session end time',
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
  AND TABLE_NAME = 'Sessions';

INSERT INTO Sessions (
    session_id, event_id, title, speaker_name, start_time, end_time
) VALUES
(1, 1, 'Opening Keynote', 'Dr. Tech', '2025-06-10 10:00:00', '2025-06-10 11:00:00'),
(2, 1, 'Future of Web Dev', 'Alice Johnson', '2025-06-10 11:15:00', '2025-06-10 12:30:00'),
(3, 2, 'AI in Healthcare', 'Charlie Lee', '2025-05-15 09:30:00', '2025-05-15 11:00:00'),
(4, 3, 'Intro to HTML5', 'Bob Smith', '2025-07-01 10:00:00', '2025-07-01 12:00:00');

select * from Sessions;
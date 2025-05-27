use javadb;
CREATE TABLE Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique resource ID',
    event_id INT COMMENT 'Associated event',
    resource_type ENUM('pdf', 'image', 'link') COMMENT 'Type of resource',
    resource_url VARCHAR(255) NOT NULL COMMENT 'Path or link to resource',
    uploaded_at DATETIME NOT NULL COMMENT 'Upload timestamp',
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);
SELECT 
    COLUMN_NAME AS 'Column Name',
    COLUMN_TYPE AS 'Data Type',
    CONCAT(
        IF(IS_NULLABLE = 'NO', 'NOT NULL ', ''),
        IF(COLUMN_KEY = 'PRI', 'PRIMARY KEY ', ''),
        IF(COLUMN_KEY = 'MUL', 'FOREIGN KEY ', ''),
        IF(EXTRA = 'auto_increment', 'AUTO_INCREMENT', '')
    ) AS 'Constraints',
    COLUMN_COMMENT AS 'Description'
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'javadb'  -- 🔁 Replace with your actual DB name
  AND TABLE_NAME = 'Resources';

INSERT INTO Resources (
    resource_id, event_id, resource_type, resource_url, uploaded_at
) VALUES
(1, 1, 'pdf', 'https://portal.com/resources/tech_meetup_agenda.pdf', '2025-05-01 10:00:00'),
(2, 2, 'image', 'https://portal.com/resources/ai_poster.jpg', '2025-04-20 09:00:00'),
(3, 3, 'link', 'https://portal.com/resources/html5_docs', '2025-06-25 15:00:00');

select * from Resources;
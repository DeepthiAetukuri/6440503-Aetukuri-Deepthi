use javadb;
CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique event identifier',
    title VARCHAR(200) NOT NULL COMMENT 'Title of the event',
    description TEXT COMMENT 'Event description',
    city VARCHAR(100) NOT NULL COMMENT 'City where event is held',
    start_date DATETIME NOT NULL COMMENT 'Start date and time of event',
    end_date DATETIME NOT NULL COMMENT 'End date and time of event',
    status ENUM('upcoming', 'completed', 'cancelled') COMMENT 'Current status of the event',
    organizer_id INT COMMENT 'User who organized the event',
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
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
  AND TABLE_NAME = 'Events';

INSERT INTO Events (
    event_id, title, description, city, start_date, end_date, status, organizer_id
) VALUES
(1, 'Tech Innovators Meetup', 'A meetup for tech enthusiasts.', 'New York', '2025-06-10 10:00:00', '2025-06-10 16:00:00', 'upcoming', 1),
(2, 'AI & ML Conference', 'Conference on AI and ML advancements.', 'Chicago', '2025-05-15 09:00:00', '2025-05-15 17:00:00', 'completed', 3),
(3, 'Frontend Development Bootcamp', 'Hands-on training on frontend tech.', 'Los Angeles', '2025-07-01 10:00:00', '2025-07-03 16:00:00', 'upcoming', 2);


select * from Events;
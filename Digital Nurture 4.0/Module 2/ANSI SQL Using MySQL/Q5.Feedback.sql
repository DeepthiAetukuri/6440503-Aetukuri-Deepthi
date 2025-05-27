use javadb;
CREATE TABLE Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'Unique feedback ID',
    user_id INT COMMENT 'User who gave feedback',
    event_id INT COMMENT 'Event for which feedback is given',
    rating INT CHECK (rating BETWEEN 1 AND 5) COMMENT 'Rating out of 5',
    comments TEXT COMMENT 'Optional comments',
    feedback_date DATE NOT NULL COMMENT 'Date of feedback',
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

SELECT 
    COLUMN_NAME AS 'Column Name',
    COLUMN_TYPE AS 'Data Type',
    CONCAT(
        IF(IS_NULLABLE = 'NO', 'NOT NULL ', ''),
        IF(COLUMN_KEY = 'PRI', 'PRIMARY KEY ', ''),
        IF(COLUMN_KEY = 'MUL', 'FOREIGN KEY ', ''), -- 'MUL' means it’s part of an index, often foreign key
        IF(EXTRA = 'auto_increment', 'AUTO_INCREMENT', ''),
        IF(COLUMN_NAME = 'rating', 'CHECK (rating BETWEEN 1 AND 5)', '') -- add custom constraint info
    ) AS 'Constraints',
    COLUMN_COMMENT AS 'Description'
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'javadb' 
  AND TABLE_NAME = 'Feedback';

INSERT INTO Feedback (
    feedback_id, user_id, event_id, rating, comments, feedback_date
) VALUES
(1, 3, 2, 4, 'Great insights!', '2025-05-16'),
(2, 4, 2, 5, 'Very informative.', '2025-05-16'),
(3, 2, 1, 3, 'Could be better.', '2025-06-11');





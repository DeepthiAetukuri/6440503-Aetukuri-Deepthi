use javadb;
SELECT 
    u.user_id,
    
    e.event_id,
    e.city,
    e.start_date
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.start_date > CURRENT_DATE
ORDER BY u.user_id, e.city, e.start_date;


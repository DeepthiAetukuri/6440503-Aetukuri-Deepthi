SELECT
    e.event_id,
    e.title,
    COUNT(r.registration_id) AS registration_count
FROM
    Events e
JOIN
    Registrations r ON e.event_id = r.event_id
LEFT JOIN
    Feedback f ON e.event_id = f.event_id
GROUP BY
    e.event_id, e.title
HAVING
    COUNT(f.feedback_id) = 0
ORDER BY
    e.title;

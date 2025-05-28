SELECT
    o.user_id AS organizer_id,
    o.full_name AS organizer_name,
    COUNT(e.event_id) AS total_events,
    SUM(CASE WHEN e.status = 'upcoming' THEN 1 ELSE 0 END) AS upcoming_events,
    SUM(CASE WHEN e.status = 'completed' THEN 1 ELSE 0 END) AS completed_events,
    SUM(CASE WHEN e.status = 'cancelled' THEN 1 ELSE 0 END) AS cancelled_events
FROM
    Users o
LEFT JOIN
    Events e ON o.user_id = e.organizer_id
GROUP BY
    o.user_id, o.full_name
ORDER BY
    o.full_name;

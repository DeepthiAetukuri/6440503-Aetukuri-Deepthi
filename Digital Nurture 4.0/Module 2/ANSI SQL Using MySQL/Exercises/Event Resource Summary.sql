SELECT
    e.event_id,
    e.title,
    COALESCE(SUM(CASE WHEN r.resource_type = 'pdf' THEN 1 ELSE 0 END), 0) AS pdf_count,
    COALESCE(SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END), 0) AS image_count,
    COALESCE(SUM(CASE WHEN r.resource_type = 'link' THEN 1 ELSE 0 END), 0) AS link_count,
    COALESCE(COUNT(r.resource_id), 0) AS total_resources
FROM
    Events e
LEFT JOIN
    Resources r ON e.event_id = r.event_id
GROUP BY
    e.event_id, e.title
ORDER BY
    e.title;

SELECT
    DATE_FORMAT(r.registration_date, '%Y-%m') AS `year_month`,
    COUNT(r.registration_id) AS registrations_count
FROM
    Registrations r
WHERE
    r.registration_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY
    `year_month`
ORDER BY
    `year_month`;

SELECT u.user_id, u.full_name, u.email, u.city
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
WHERE r.registration_id IS NULL;

INSERT INTO users (
    name,
    email,
    password_hash,
    created_at,
    created_by
) VALUES
(
    'Arpitha',
    'arpitha@yopmail.com',
    '$2b$10$9oGyreIxB5TaapjEZzG2NO.9JY/Zh/CCcBR2IHjvAkpym10JBqawO',
    CURRENT_TIMESTAMP,
    'SYSTEM'
),
(
    'Rahul Sharma',
    'rahul@yopmail.com',
    '$2b$10$9oGyreIxB5TaapjEZzG2NO.9JY/Zh/CCcBR2IHjvAkpym10JBqawO',
    CURRENT_TIMESTAMP,
    'SYSTEM'
),
(
    'Admin User',
    'admin@shopeasy.com',
    '$2b$10$vAhpkNwPOaQtnXyJ3rZSdOYO9aIXtZxeolrD3wMm8nB3gsw/xD2dm',
    CURRENT_TIMESTAMP,
    'SYSTEM'
);
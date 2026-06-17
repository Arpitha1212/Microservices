
-- Users (passwords are real BCrypt hashes, so login testing works later)
-- password123 -> for Arpitha & Rahul
-- admin123    -> for Admin
INSERT INTO users (id, name, email, password_hash) VALUES
  (1, 'Arpitha', 'arpitha@yopmail.com', '$2b$10$9oGyreIxB5TaapjEZzG2NO.9JY/Zh/CCcBR2IHjvAkpym10JBqawO'),
  (2, 'Rahul Sharma', 'rahul@yopmail.com', '$2b$10$9oGyreIxB5TaapjEZzG2NO.9JY/Zh/CCcBR2IHjvAkpym10JBqawO'),
  (3, 'Admin User', 'admin@shopeasy.com', '$2b$10$vAhpkNwPOaQtnXyJ3rZSdOYO9aIXtZxeolrD3wMm8nB3gsw/xD2dm');

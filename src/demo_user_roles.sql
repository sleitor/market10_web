UPDATE demo.user_roles
SET login = 'q1q1', roles = 'ROLE_USER'
WHERE uuid = 1;
UPDATE demo.user_roles
SET login = 'admin', roles = 'ROLE_ADMIN'
WHERE uuid = 2;
UPDATE demo.user_roles
SET login = 'admin', roles = 'ROLE_USER'
WHERE uuid = 3;
UPDATE demo.user_roles
SET login = '1q1q', roles = 'ROLE_USER'
WHERE uuid = 4;
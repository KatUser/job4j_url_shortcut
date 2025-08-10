CREATE TABLE app_user_role
(
    id SERIAL PRIMARY KEY,
    role_id     INT REFERENCES role(id) ON DELETE CASCADE,
    app_user_id INT REFERENCES app_user(id) ON DELETE CASCADE
);
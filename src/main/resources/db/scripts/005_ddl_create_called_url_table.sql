CREATE TABLE called_url
(
    id SERIAL PRIMARY KEY,
    app_user_id INT REFERENCES app_user(id) ON DELETE CASCADE,
    url TEXT NOT NULL,
    count INT NOT NULL
);
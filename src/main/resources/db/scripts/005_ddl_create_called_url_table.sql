CREATE TABLE called_url
(
    id SERIAL PRIMARY KEY,
    app_user_id INT REFERENCES app_user(id),
    url TEXT NOT NULL UNIQUE,
    count INT NOT NULL
);
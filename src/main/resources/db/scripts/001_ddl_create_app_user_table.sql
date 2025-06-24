CREATE TABLE app_user
(
    id SERIAL PRIMARY KEY,
    user_name VARCHAR NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    registration_date TIMESTAMP NOT NULL
);

CREATE TABLE site
(
    id SERIAL PRIMARY KEY,
    site_name VARCHAR NOT NULL UNIQUE,
    registration_date TIMESTAMP NOT NULL
);

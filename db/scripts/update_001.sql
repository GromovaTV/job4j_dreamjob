CREATE TABLE post (
id SERIAL PRIMARY KEY,
name TEXT
);

CREATE TABLE candidate (
id SERIAL PRIMARY KEY,
name TEXT UNIQUE
);

CREATE TABLE users (
id SERIAL PRIMARY KEY,
name TEXT,
email TEXT UNIQUE,
password TEXT
);
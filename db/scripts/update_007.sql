CREATE TABLE post (
id SERIAL PRIMARY KEY,
name varchar,
created TIMESTAMP
);

CREATE TABLE candidate (
id SERIAL PRIMARY KEY,
name varchar,
created TIMESTAMP,
city_id integer
);

CREATE TABLE city (
id SERIAL PRIMARY KEY,
name varchar
);

CREATE TABLE users (
id SERIAL PRIMARY KEY,
name varchar,
email varchar UNIQUE,
password varchar
);
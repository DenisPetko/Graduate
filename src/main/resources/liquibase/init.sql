--liquibase formatted sql
-- changeset derkachev:1000000-1
-- comment: Initial creation of table users
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    user_name  VARCHAR(32) NOT NULL,
    password   VARCHAR(250) NOT NULL,
    email      VARCHAR(32) NOT NULL,
    role       VARCHAR(5) NOT NULL,
    first_name VARCHAR(32),
    last_name  VARCHAR(32),
    phone      VARCHAR(18)
);


--liquibase formatted sql
-- changeset derkachev:1000000-1
-- comment: Initial creation of table users
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    user_name  VARCHAR(32)  NOT NULL UNIQUE,
    password   VARCHAR(250) NOT NULL,
    role       VARCHAR(5)   NOT NULL,
    first_name VARCHAR(32),
    last_name  VARCHAR(32),
    phone      VARCHAR(18),
    image_id   VARCHAR(255)
);

CREATE TABLE ads
(
    id          SERIAL PRIMARY KEY,
    author_id   INT      NOT NULL,
    image_id    VARCHAR(255),
    price       INT         NOT NULL,
    title       VARCHAR(32) NOT NULL,
    description VARCHAR(500)
);

CREATE TABLE comments
(
    id                SERIAL PRIMARY KEY,
    author_id         INT        NOT NULL,
    ads_id            INT        NOT NULL,
    author_image_id   VARCHAR(255)        NOT NULL,
    author_first_name VARCHAR(50)   NOT NULL,
    created_at        TIMESTAMP(50) NOT NULL,
    text              VARCHAR(32)
);

-- changeset Couldlie:100
CREATE TABLE image
(
    id                VARCHAR PRIMARY KEY,
    image bytea       NOT NULL
);




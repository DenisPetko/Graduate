--liquibase formatted sql
-- changeset derkachev:1000000-1
-- comment: Initial creation of table users
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    user_name  VARCHAR(32)  NOT NULL,
    password   VARCHAR(250) NOT NULL,
    email      VARCHAR(32)  NOT NULL,
    role       VARCHAR(5)   NOT NULL,
    first_name VARCHAR(32),
    last_name  VARCHAR(32),
    phone      VARCHAR(18),
    image_id    VARCHAR(255)
);

CREATE TABLE ads
(
    id          BIGSERIAL PRIMARY KEY,
    author_id   BIGINT      NOT NULL,
    image_id    VARCHAR(255),
    price       INT         NOT NULL,
    title       VARCHAR(32) NOT NULL,
    description VARCHAR(500)
);

CREATE TABLE comments
(
    id                BIGSERIAL PRIMARY KEY,
    author_id         BIGINT        NOT NULL,
    ads_id            BIGINT        NOT NULL,
    author_image_id   BIGINT        NOT NULL,
    author_first_name VARCHAR(50)   NOT NULL,
    created_at        TIMESTAMP(50) NOT NULL,
    text              VARCHAR(32)
);

-- changeset Couldlie:100
CREATE TABLE image
(
    id    VARCHAR PRIMARY KEY,
    image bytea NOT NULL
);




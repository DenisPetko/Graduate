--liquibase formatted sql
-- changeset derkachev:1000000-1
-- comment: Initial creation of table users
CREATE TABLE "users"
(
    "id"         UUID    NOT NULL,
    "created_at" TIMESTAMP WITHOUT TIME ZONE,
    "email"      VARCHAR NOT NULL,
    "first_name"  VARCHAR NOT NULL,
    "last_name"   VARCHAR NOT NULL,
    "phone"      VARCHAR NOT NULL,
    "image"      bytea ,

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);


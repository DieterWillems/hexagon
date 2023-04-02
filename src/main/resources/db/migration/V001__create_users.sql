CREATE TABLE IF NOT EXISTS users (
    id        bigserial primary key,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    nick_name  VARCHAR(255),
    email     VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS users
(
    id                   SERIAL              NOT NULL PRIMARY KEY,
    username             VARCHAR(50)         NOT NULL,
    email                VARCHAR(100) UNIQUE NOT NULL,
    password             TEXT                NOT NULL,
    verification_code    TEXT,
    verification_expires TIMESTAMP,
    enabled              BOOLEAN
    );

CREATE TABLE IF NOT EXISTS restaurant
(
    id               SERIAL NOT NULL PRIMARY KEY,
    name             VARCHAR(100),
    description      TEXT,
    location         TEXT,
    rating           DECIMAL(3, 1),
    count_of_reviews INT,
    work_time_open   TIME,
    work_time_closed TIME
    );

CREATE TABLE IF NOT EXISTS tags
(
    id   SERIAL NOT NULL PRIMARY KEY,
    name TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS restaurant_tags
(
    restaurant_id INT,
    tag_id        INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
    );

CREATE TABLE IF NOT EXISTS restaurant_reviews
(
    id            SERIAL NOT NULL PRIMARY KEY,
    restaurant_id INT,
    user_id       INT,
    rating        DECIMAL(3, 1) CHECK (rating BETWEEN 1.0 AND 5.0),
    content       TEXT,
    created_at    TIMESTAMP,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
    );

CREATE TABLE IF NOT EXISTS reservations
(
    id               SERIAL NOT NULL PRIMARY KEY,
    user_id          INT,
    table_id         INT,
    restaurant_id    INT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (table_id) REFERENCES tables (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
    );

CREATE TABLE IF NOT EXISTS tables
(
    id               SERIAL NOT NULL PRIMARY KEY,
    reservation_time TIMESTAMP,
    capacity         INT CHECK (capacity IN (1, 2, 3, 4, 5, 6, 7, 8)),
    restaurant_id    INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
    );

CREATE TABLE IF NOT EXISTS dishes
(
    id            SERIAL NOT NULL PRIMARY KEY,
    name          TEXT,
    restaurant_id INT,
    price         DECIMAL(6, 2),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
    );

CREATE TABLE IF NOT EXISTS ingredients
(
    id       SERIAL NOT NULL PRIMARY KEY,
    name     TEXT,
    quantity INT,
    unit     VARCHAR(30)
    );

CREATE TABLE IF NOT EXISTS dishes_ingredients
(
    ingredient_id INT,
    dish_id       INT,
    quantity      DECIMAL(6,2),
    PRIMARY KEY (ingredient_id, dish_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (id),
    FOREIGN KEY (dish_id) REFERENCES dishes (id)
    );
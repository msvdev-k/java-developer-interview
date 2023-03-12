USE lesson05;

-- --------------------------------
-- Фильмы
-- --------------------------------
DROP TABLE IF EXISTS films;
CREATE TABLE films (
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    name     TEXT,
    duration TIME
);

-- --------------------------------
-- Сеансы
-- --------------------------------
DROP TABLE IF EXISTS sessions;
CREATE TABLE sessions (
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    film_id      INTEGER REFERENCES films (id) ON DELETE RESTRICT
                                               ON UPDATE RESTRICT,
    start_film   DATETIME,
    ticket_price NUMERIC (8, 2) 
);

-- --------------------------------
-- Билеты
-- --------------------------------
DROP TABLE IF EXISTS tickets;
CREATE TABLE tickets (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    session_id INTEGER REFERENCES sessions (id) ON DELETE RESTRICT
                                                ON UPDATE RESTRICT
);

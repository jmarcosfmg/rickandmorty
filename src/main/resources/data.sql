
CREATE TABLE characters (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    status VARCHAR(7) NOT NULL,
    species VARCHAR(25) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    origin INT,
    location INT,
    created DATE
);

INSERT INTO characters (id, name, status, species, gender, origin, location, created) VALUES (1, 'Morty', 'ALIVE', 'human', 'MALE', 1, 1, '2023-08-26');

CREATE TABLE locations (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dimension VARCHAR(50) NOT NULL,
    created DATE
);

INSERT INTO locations (id, name, dimension, created) VALUES (1, 'Earth', 'c-137', '2023-08-26');

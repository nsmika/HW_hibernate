CREATE SCHEMA IF NOT EXISTS NETOLOGY

    CREATE TABLE IF NOT EXISTS NETOLOGY.PERSONS
    (
        name           VARCHAR(255),
        surname        VARCHAR(255),
        age            INT,
        phone_number   VARCHAR(255),
        city_of_living VARCHAR(255),
        PRIMARY KEY (name, surname, age)
    );

INSERT INTO NETOLOGY.PERSONS (name, surname, age, phone_number, city_of_living)
VALUES ('Ivan', 'Ivanov', 30, '+7-900-123-4567', 'Moscow'),
       ('Petr', 'Petrov', 40, '+7-901-123-4567', 'Saint Petersburg'),
       ('Sergey', 'Sergeev', 25, '+7-902-123-4567', 'Kazan');

SELECT name, surname
FROM NETOLOGY.PERSONS
WHERE city_of_living = 'MOSCOW';

SELECT *
FROM NETOLOGY.PERSONS
WHERE age > 27
ORDER BY age DESC;

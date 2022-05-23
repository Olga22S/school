CREATE TABLE car
(
    car_id INT PRIMARY KEY,
    brand  VARCHAR,
    model  VARCHAR,
    price  MONEY
);

CREATE TABLE person
(
    person_id           INT PRIMARY KEY,
    name                VARCHAR(30),
    age                 INT,
    has_driving_license BOOLEAN,
    car_id              INT NOT NULL REFERENCES car (car_id)
);

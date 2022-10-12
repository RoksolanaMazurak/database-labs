CREATE DATABASE IF NOT EXISTS mazurak;
USE mazurak;

DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS car_model;
DROP TABLE IF EXISTS card_bonus;
DROP TABLE IF EXISTS card_has_bonus;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS client_card;
DROP TABLE IF EXISTS driver;
DROP TABLE IF EXISTS driver_has_car;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS reservation;

CREATE TABLE car (
    id INT NOT NULL AUTO_INCREMENT,
    car_number CHAR(8) NOT NULL,
    car_model_id INT NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (id)
);

CREATE TABLE car_model (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50)  NOT NULL,
    company VARCHAR(50)  NOT NULL,
    class VARCHAR(50)  NOT NULL,
    seat_number INT NOT NULL,
    CONSTRAINT car_model_pk PRIMARY KEY (id)
);

CREATE TABLE card_bonus (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30)  NOT NULL,
    CONSTRAINT card_bonus_pk PRIMARY KEY (id)
);

CREATE TABLE card_has_bonus (
    card_bonus_id INT NOT NULL,
    client_card_id INT NOT NULL,
    bonus_value INT NOT NULL
);

CREATE TABLE client (
    id INT NOT NULL AUTO_INCREMENT,
    surname VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    password char(30) NOT NULL,
    phone char(12) NOT NULL,
    email VARCHAR(100) NULL,
    city VARCHAR(100) NULL,
    street_address VARCHAR(100)  NULL,
    client_card_id INT NOT NULL,
    CONSTRAINT client_pk PRIMARY KEY (id)
);

CREATE TABLE client_card (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT client_card_pk PRIMARY KEY (id)
);

CREATE TABLE driver (
    id INT NOT NULL AUTO_INCREMENT,
    surname VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NULL,
    is_available BOOLEAN NOT NULL,
    rating_id INT NOT NULL,
    CONSTRAINT driver_pk PRIMARY KEY (id)
);

CREATE TABLE driver_has_car (
    id INT NOT NULL AUTO_INCREMENT,
    car_id INT NOT NULL,
    driver_id INT NOT NULL,
    CONSTRAINT driver_has_car_pk PRIMARY KEY (id)
);

CREATE TABLE payment (
    id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL,
    time TIMESTAMP NOT NULL,
    card_number varchar(16) NULL,
    transaction_id varchar(15) NOT NULL,
    CONSTRAINT payment_pk PRIMARY KEY (id)
);

CREATE TABLE rating (
	id INT NOT NULL AUTO_INCREMENT,
    value INT NOT NULL,
    client_id INT NOT NULL,
    CONSTRAINT rating_pk PRIMARY KEY (id)
);

CREATE TABLE reservation (
    id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    time TIMESTAMP NOT NULL,
    payment_id INT NOT NULL,
    start_address VARCHAR(100) NOT NULL,
    final_address VARCHAR(100) NOT NULL,
    car_id INT NOT NULL,
    CONSTRAINT reservation_pk PRIMARY KEY (id)
);

-- Reference: car_car_model (table: car)
ALTER TABLE car ADD CONSTRAINT car_car_model
    FOREIGN KEY (car_model_id)
    REFERENCES car_model (id);

-- Reference: card_has_bonus_card_bonus (table: card_has_bonus)
ALTER TABLE card_has_bonus ADD CONSTRAINT card_has_bonus_card_bonus
    FOREIGN KEY (card_bonus_id)
    REFERENCES card_bonus (id);

-- Reference: card_has_bonus_client_card (table: card_has_bonus)
ALTER TABLE card_has_bonus ADD CONSTRAINT card_has_bonus_client_card
    FOREIGN KEY (client_card_id)
    REFERENCES client_card (id);

-- Reference: client_client_card (table: client)
ALTER TABLE client ADD CONSTRAINT client_client_card
    FOREIGN KEY (client_card_id)
    REFERENCES client_card (id);

-- Reference: driver_car_car (table: driver_has_car)
ALTER TABLE driver_has_car ADD CONSTRAINT driver_car_car
    FOREIGN KEY (car_id)
    REFERENCES car (id);

-- Reference: driver_car_driver (table: driver_has_car)
ALTER TABLE driver_has_car ADD CONSTRAINT driver_car_driver
    FOREIGN KEY (driver_id)
    REFERENCES driver (id);

-- Reference: driver_rating (table: driver)
ALTER TABLE driver ADD CONSTRAINT driver_rating
    FOREIGN KEY (rating_id)
    REFERENCES rating (id);

-- Reference: rating_client (table: rating)
ALTER TABLE rating ADD CONSTRAINT rating_client
    FOREIGN KEY (client_id)
    REFERENCES client (id);

-- Reference: reservation_car (table: reservation)
ALTER TABLE reservation ADD CONSTRAINT reservation_car
    FOREIGN KEY (car_id)
    REFERENCES car (id);

-- Reference: reservation_client (table: reservation)
ALTER TABLE reservation ADD CONSTRAINT reservation_client
    FOREIGN KEY (client_id)
    REFERENCES client (id);

-- Reference: reservation_payment (table: reservation)
ALTER TABLE reservation ADD CONSTRAINT reservation_payment
    FOREIGN KEY (payment_id)
    REFERENCES payment (id);
    
INSERT INTO car_model (id, name, company, class, seat_number) VALUES
(1, 'Skoda Rapid', 'Skoda Auto', 'comfort', 4),
(2, 'Renault Logan', 'Renault', 'comfort', 4),
(3, 'Mercedes-Benz Sprinter', 'Mercedes', 'econom', 8),
(4, 'Volkswagen Crafter', 'Volkswagen', 'comfort', 8),
(5, 'Toyota Camry', 'Toyota', 'premium', 4),
(6, 'Skoda Octavia', 'Skoda Auto', 'econom', 4),
(7, 'Mercedes T1', 'Mercedes', 'econom', 16),
(8, 'Volkswagen Polo Sedan', 'Volkswagen', 'econom', 4),
(9, 'Mercedes Vito', 'Mercedes', 'premium', 8),
(10, 'Toyota Corolla', 'Toyota', 'premium', 4);

INSERT INTO car (id, car_number, car_model_id) VALUES
(1, 'BC1111AK', 4),
(2, 'AK3121AK', 10),
(3, 'AC9811ZA', 1),
(4, 'BC1111AK', 8),
(5, 'AA1213ZA', 9),
(6, 'BC1111AK', 5),
(7, 'AA2657AK', 6),
(8, 'BC8372CI', 3),
(9, 'BC7561AK', 7),
(10, 'AK8216CI', 2);

INSERT INTO card_bonus (id, name) VALUES
(1, 'free econom class trip'),
(2, 'free comfort class trip'),
(3, 'free premium class trip'),
(4, '55% off for the next trip'),
(5, '50% off for the next trip'),
(6, '25% off for the next trip'),
(7, '15% off for the next trip'),
(8, '10% off for the next trip'),
(9, '5% off for the next trip'),
(10, 'free trip');

INSERT INTO client_card (id, name) VALUES
(1, 'golden'),
(2, 'silver');

INSERT INTO client (id, surname, name, password, phone, email, city, street_address,  client_card_id) VALUES
(1, 'Klymenko', 'Oleksandra', 'password1', '0980618738', 'klymenko.sasha@gmail.com', 'Lviv', 'Baturynska street, 58', 1),
(2, 'Melnyk', 'Pavlo', 'password2', '0930818730', 'klymenko.sasha@gmail.com', 'Ivano-Frankivsk', 'Trusha Street, 2c', 1),
(3, 'Havrylyuk', 'Andrii', 'password3', '0951518739', 'havrylyuk.andrii@gmail.com', 'Chernivtsi', 'Shevchenko street, 21', 1),
(4, 'Boyko', 'Oksana', 'password4', '0674518721', 'boyko.oksana@gmail.com', 'Lviv', 'Kolisna, 12', 2),
(5, 'Karpenko', 'Olga', 'password5', '0988918702', 'karpenko.olga@gmail.com', 'Ivano-Frankivsk', 'Zaklynskykh Street, 4', 1),
(6, 'Rudenko', 'Maria', 'password6', '0991258782', 'rudenko.maria@gmail.com', 'Lviv', 'Svitankova, 1a', 1),
(7, 'Lysenko', 'Mykola', 'password7', '0930868737', 'lysenko.mykola@gmail.com', 'Chernivtsi', 'Holovna street, 14', 2),
(8, 'Polishchuk', 'Nazar', 'password8', '0990618963', 'polishchuk.nazik@gmail.com', 'Ivano-Frankivsk', 'Makohona Street, 1b', 2),
(9, 'Kovalenko', 'Oleg', 'password9', '067051871', 'kovalenko.oleg@gmail.com', 'Lviv', 'Yaroslava Mudroho, 5', 2),
(10, 'Shevchenko', 'Natalia', 'password10', '0985211722', 'shevchenko.natalia@gmail.com', 'Ivano-Frankivsk', 'Karpatska Street, 25', 2);

INSERT INTO rating (id, value, client_id) VALUES
(1, 5, 2),
(2, 2, 1),
(3, 3, 5),
(4, 5, 9),
(5, 4, 10),
(6, 1, 7),
(7, 5, 3),
(8, 3, 8),
(9, 2, 6),
(10, 3, 4);

INSERT INTO driver (id, surname, name, gender, is_available, rating_id) VALUES
(1, 'Savchenko', 'Oleg', 'male', true, 3),
(2, 'Shevchuk', 'Ivan', 'male', false, 2),
(3, 'Tkachuk', 'Oleksandr', 'male', true, 5),
(4, 'Kravchuk', 'Bogdana', 'female', true, 10),
(5, 'Boychuk', 'Yana', 'female', true, 4),
(6, 'Kharchenko', 'Vasyl', 'male', false, 9),
(7, 'Ponomarenko', 'Olena', 'female', true, 6),
(8, 'Kuzmenko', 'Nazarii', 'male', false, 8),
(9, 'Sydorenko', 'Oksana', 'female', true, 7),
(10, 'Kovalenko', 'Viktoria', 'female', true, 1);

INSERT INTO driver_has_car (id, car_id, driver_id) VALUES
(1, 7, 10),
(2, 8, 1),
(3, 4, 8),
(4, 5, 9),
(5, 1, 3),
(6, 6, 2),
(7, 9, 7),
(8, 3, 4),
(9, 2, 6),
(10, 10, 5);

INSERT INTO payment (id, type, time, card_number, transaction_id) VALUES
(1, 'credit card', '2022-10-08 16:00:00.000', '1234567894512639', 2),
(2, 'credit card', '2022-10-08 12:00:00.000', '1256321789654123', 3),
(3, 'credit card', '2022-10-08 10:20:00.000', '0296327665931223', 8),
(4, 'credit card', '2022-10-08 09:00:00.000', '8978651437964852', 1),
(5, 'credit card', '2022-10-07 17:15:00.000', '15426398657421', 9),
(6, 'credit card', '2022-10-07 13:30:00.000', '9874563219647853', 4),
(7, 'credit card', '2022-10-06 22:00:00.000', '9874563215747779', 10),
(8, 'credit card', '2022-10-06 20:30:00.000', '2517369137864268', 7),
(9, 'credit card', '2022-10-06 13:00:00.000', '9674359965658314', 5),
(10, 'credit card', '2022-10-06 10:00:00.000', '3625157986332125', 6);

INSERT INTO reservation (id, client_id, time, payment_id, start_address, final_address, car_id) VALUES
(1, 1, '2022-10-08 20:00:00.000', 5, 'Yaroslava Mudroho, 5', 'Baturynska street, 58', 2),
(2, 5, '2022-10-08 14:00:00.000', 8, 'Shevchenko street, 21', 'Svitankova, 1a', 5),
(3, 9, '2022-10-08 11:20:00.000', 1, 'Holovna street, 14','Svitankova, 1a', 8),
(4, 3, '2022-10-08 09:30:00.000', 7, 'Makohona Street, 1b', 'Trusha Street, 2c', 9),
(5, 10, '2022-10-07 18:15:00.000', 9, 'Holovna street,  14', 'Trusha Street, 2c',1),
(6, 7, '2022-10-07 14:30:00.000', 4, 'Trusha Street, 2c', 'Svitankova, 1a',  6),
(7, 2, '2022-10-06 22:30:00.000', 10, 'Makohona Street, 1b', 'Shevchenko street, 21', 10),
(8, 4, '2022-10-06 21:30:00.000', 2, 'Svitankova, 1a',  'Holovna street, 14', 7),
(9, 8, '2022-10-06 14:00:00.000', 3, 'Shevchenko street, 21',  'Holovna street, 14', 3),
(10, 6, '2022-10-06 11:00:00.000', 6, 'Baturynska street, 58', 'Zaklynskykh Street, 4', 4);

CREATE INDEX client_surname ON client(surname);
CREATE INDEX driver_surname ON driver(surname);
CREATE INDEX car_name ON car(name);

SHOW INDEX FROM client;
SHOW INDEX FROM driver;
SHOW INDEX FROM car;

-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-09-13 11:00:03.812

-- tables
-- Table: car
CREATE TABLE car (
    id int  NOT NULL,
    VIN char(17)  NOT NULL,
    car_model_id int  NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (id)
);

-- Table: car_model
CREATE TABLE car_model (
    id int  NOT NULL,
    name varchar(50)  NOT NULL,
    company varchar(50)  NOT NULL,
    class varchar(50)  NOT NULL,
    seat_number int  NOT NULL,
    CONSTRAINT car_model_pk PRIMARY KEY (id)
);

-- Table: card_bonus
CREATE TABLE card_bonus (
    id int  NOT NULL,
    name varchar(30)  NOT NULL,
    CONSTRAINT card_bonus_pk PRIMARY KEY (id)
);

-- Table: card_has_bonus
CREATE TABLE card_has_bonus (
    card_bonus_id int  NOT NULL,
    client_card_id int  NOT NULL,
    bonus_value int  NOT NULL
);

-- Table: client
CREATE TABLE client (
    id int  NOT NULL,
    surname varchar(50)  NOT NULL,
    name varchar(50)  NOT NULL,
    password char(30)  NOT NULL,
    phone char(12)  NOT NULL,
    email varchar(100)  NULL,
    city varchar(100)  NULL,
    street_address varchar(100)  NULL,
    client_card_id int  NOT NULL,
    CONSTRAINT client_pk PRIMARY KEY (id)
);

-- Table: client_card
CREATE TABLE client_card (
    id int  NOT NULL,
    name varchar(30)  NOT NULL,
    CONSTRAINT client_card_pk PRIMARY KEY (id)
);

-- Table: driver
CREATE TABLE driver (
    id int  NOT NULL,
    surname varchar(50)  NOT NULL,
    name varchar(50)  NOT NULL,
    gender varchar(10)  NULL,
    is_available boolean  NOT NULL,
    rating_value int  NOT NULL,
    CONSTRAINT driver_pk PRIMARY KEY (id)
);

-- Table: driver_has_car
CREATE TABLE driver_has_car (
    id int  NOT NULL,
    car_id int  NOT NULL,
    driver_id int  NOT NULL,
    CONSTRAINT driver_has_car_pk PRIMARY KEY (id)
);

-- Table: payment
CREATE TABLE payment (
    id int  NOT NULL,
    type varchar(50)  NOT NULL,
    time timestamp  NOT NULL,
    card_number int  NULL,
    transaction_id varchar(15)  NOT NULL,
    CONSTRAINT payment_pk PRIMARY KEY (id)
);

-- Table: rating
CREATE TABLE rating (
    value int  NOT NULL,
    client_id int  NOT NULL,
    CONSTRAINT rating_pk PRIMARY KEY (value)
);

-- Table: reservation
CREATE TABLE reservation (
    id int  NOT NULL,
    client_id int  NOT NULL,
    time timestamp  NOT NULL,
    payment_id int  NOT NULL,
    start_address varchar(100)  NOT NULL,
    final_address varchar(100)  NOT NULL,
    car_id int  NOT NULL,
    CONSTRAINT reservation_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: car_car_model (table: car)
ALTER TABLE car ADD CONSTRAINT car_car_model
    FOREIGN KEY (car_model_id)
    REFERENCES car_model (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: card_has_bonus_card_bonus (table: card_has_bonus)
ALTER TABLE card_has_bonus ADD CONSTRAINT card_has_bonus_card_bonus
    FOREIGN KEY (card_bonus_id)
    REFERENCES card_bonus (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: card_has_bonus_client_card (table: card_has_bonus)
ALTER TABLE card_has_bonus ADD CONSTRAINT card_has_bonus_client_card
    FOREIGN KEY (client_card_id)
    REFERENCES client_card (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: client_client_card (table: client)
ALTER TABLE client ADD CONSTRAINT client_client_card
    FOREIGN KEY (client_card_id)
    REFERENCES client_card (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: driver_car_car (table: driver_has_car)
ALTER TABLE driver_has_car ADD CONSTRAINT driver_car_car
    FOREIGN KEY (car_id)
    REFERENCES car (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: driver_car_driver (table: driver_has_car)
ALTER TABLE driver_has_car ADD CONSTRAINT driver_car_driver
    FOREIGN KEY (driver_id)
    REFERENCES driver (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: driver_rating (table: driver)
ALTER TABLE driver ADD CONSTRAINT driver_rating
    FOREIGN KEY (rating_value)
    REFERENCES rating (value)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: rating_client (table: rating)
ALTER TABLE rating ADD CONSTRAINT rating_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: reservation_car (table: reservation)
ALTER TABLE reservation ADD CONSTRAINT reservation_car
    FOREIGN KEY (car_id)
    REFERENCES car (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: reservation_client (table: reservation)
ALTER TABLE reservation ADD CONSTRAINT reservation_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: reservation_payment (table: reservation)
ALTER TABLE reservation ADD CONSTRAINT reservation_payment
    FOREIGN KEY (payment_id)
    REFERENCES payment (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.


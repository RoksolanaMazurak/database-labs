-- 1
-- select all avaible drivers
SELECT id, surname, name
FROM mazurak.driver
WHERE is_available = true;

-- 2
-- select all drivers with rating value 3+
SELECT surname, name, rating_value
FROM mazurak.driver
WHERE rating_value > 3;

-- 3
-- select all female drivers
SELECT surname, gender
FROM mazurak.driver
WHERE gender = 'female';

-- 4
-- select all customers with golden client cards
SELECT client.id, surname
FROM mazurak.client
JOIN client_card ON client_card.id = client.client_card_id
WHERE client_card.name = 'golden card';

-- 5
-- select all clients traveling on a certain car after the date specified
SELECT client_id, time
FROM mazurak.reservation
JOIN client ON reservation.client_id = client.id
WHERE (EXTRACT(DAY FROM time) > 11) AND car_id = 1;

-- 6
-- select drivers and vin
SELECT surname, VIN
FROM mazurak.driver_has_car
JOIN driver ON driver.id = driver_has_car.driver_id
JOIN car ON car.id = driver_has_car.car_id;

-- 7
-- select all clients who have been paid by credit after September 11
SELECT client_id, type
FROM mazurak.reservation
JOIN payment ON payment_id = payment.id
WHERE type = 'credit card' AND EXTRACT(DAY FROM payment.time) > 11;

-- 8 
-- select emails for all clients with golden bonus card
SELECT email
FROM mazurak.client
JOIN client_card ON client_card.id = client.client_card_id
WHERE client.client_card_id = 1;

-- 9
-- select all cars with Lviv VIN
SELECT VIN, car_model.name
FROM mazurak.car
JOIN car_model ON car_model.id = car.car_model_id
WHERE VIN RLIKE '^BC';

-- 10
SELECT driver.id , surname,
CASE rating_value
WHEN rating_value < 3 THEN 'bad driver'
ELSE 'good driver'
END AS rating
FROM mazurak.driver
JOIN rating ON rating_value = rating.value;



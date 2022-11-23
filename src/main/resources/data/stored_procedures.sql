USE mazurak;

DROP PROCEDURE IF EXISTS param_insert_car_model;
DELIMITER //
CREATE PROCEDURE param_insert_car_model(
    IN new_name VARCHAR(100),
    IN new_company VARCHAR(50),
    IN new_class VARCHAR(25),
    IN new_seat_number INT)
BEGIN
    INSERT INTO car_model(name, company, class, seat_number)
    VALUES (new_name, new_company, new_class, new_seat_number);
    SELECT * FROM mazurak.car_model WHERE name = new_name;
END //

DROP PROCEDURE IF EXISTS connect_driver_and_car //
CREATE PROCEDURE connect_driver_and_car(
    IN driver_surname VARCHAR(50),
    IN car_num VARCHAR(50))
BEGIN
    DECLARE driverID, carID INT;
    SELECT id INTO driverID FROM driver WHERE surname = driver_surname;
    SELECT id INTO carID FROM car WHERE car_number = car_num;
    IF (carID IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Car does not exist';
    END IF;
    IF (driverID IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Driver does not exist';
    END IF;
    INSERT INTO driver_has_car (driver_id, car_id) VALUES (driverID, carID);
END //

DROP PROCEDURE IF EXISTS insert_drivers;
DELIMITER //
CREATE PROCEDURE insert_drivers()
BEGIN
    DECLARE maxID INT;
    DECLARE iterator INT;
    SET iterator = 1;
    SELECT MAX(id)+1 INTO maxID FROM client;
    IF maxID IS NULL THEN SET maxID=1;
    END IF;
    WHILE iterator <= 10 DO INSERT INTO driver(surname, name, gender, rating_id)
    VALUES (CONCAT('NoSurname - ', maxID), CONCAT('NoName - ', maxID),
            CONCAT('NoGender - ', maxID), 1);
    SET maxID = maxID + 1;
    SET iterator = iterator + 1;
    END WHILE ;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS max_seat_number;
DELIMITER //
CREATE PROCEDURE max_seat_number()
BEGIN
    SELECT get_max_seat_car() AS maxSeatNum FROM car_model;

END //
DELIMITER ;

DROP PROCEDURE IF EXISTS cursor_create_tables_by_client_name;
DELIMITER //
CREATE PROCEDURE cursor_create_tables_by_client_name()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE client_name VARCHAR(50);
    DECLARE client_cursor CURSOR
        FOR SELECT name FROM client;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN client_cursor;
    cursor_loop: LOOP
        FETCH client_cursor INTO client_name;
        IF (done = true) THEN LEAVE cursor_loop;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', client_name, DATE_FORMAT(NOW(), "_%Y_%m_%d_%H_%i_%s"), ' (', client_name, 'Col INT);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE client_cursor;
END  //
DELIMITER ;
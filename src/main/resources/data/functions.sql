USE mazurak;

DROP FUNCTION IF EXISTS get_max_seat_car;
DELIMITER //
CREATE FUNCTION get_max_seat_car()
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN (SELECT MAX(seat_number) FROM car_model);

END //
DELIMITER ;
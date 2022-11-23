USE mazurak;

DROP TRIGGER IF EXISTS add_car_check;
DELIMITER //
CREATE TRIGGER add_car_check
    BEFORE INSERT
    ON car FOR EACH ROW
BEGIN
    IF (new.car_shop_id NOT IN (SELECT id FROM car_shop)) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No such car shop';
    END IF;
END //

DROP TRIGGER IF EXISTS update_car_shop //
CREATE TRIGGER update_car_shop
    BEFORE UPDATE
    ON car FOR EACH ROW
BEGIN
    IF (new.car_shop_id NOT IN (SELECT id FROM car_shop)) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No such car shop';
    END IF;
END //

DROP TRIGGER IF EXISTS update_car_shop_check //
CREATE TRIGGER update_car_shop_check
    BEFORE UPDATE
    ON car_shop FOR EACH ROW
BEGIN
    IF (old.id IN (SELECT car_shop_id FROM car)) THEN
        SIGNAL SQLSTATE '45000'
            set message_text = 'Can`t update car shop, because it refers to cars.';
    END IF;
END //

DROP TRIGGER IF EXISTS delete_car_shop_check //
CREATE TRIGGER delete_car_shop_check
    BEFORE DELETE
    ON car_shop FOR EACH ROW
BEGIN
    IF (old.id IN (SELECT car_shop_id FROM car)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Can`t delete car shop, because it refers to cars.';
    END IF;
END //

DROP TRIGGER IF EXISTS delete_reservation;
DELIMITER //
CREATE TRIGGER delete_reservation
    BEFORE DELETE
    ON reservation
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Delete error for reservation';
END //
DELIMITER ;

drop trigger if exists forbid_update_reservation;
DELIMITER //
create trigger forbid_update_reservation
    before update
    on client_card for each row
begin
    signal sqlstate '45000'
        set message_text = 'Update error for client card';
end //
DELIMITER ;

DROP TRIGGER IF EXISTS after_delete_driver_min_6Row;
DELIMITER //
CREATE TRIGGER after_delete_driver_min_6Row
    AFTER DELETE
    ON driver FOR EACH ROW
BEGIN
    IF(SELECT COUNT(*) FROM mazurak.driver)<6
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Delete error MIN cardinality';
    END IF;
END //
DELIMITER ;
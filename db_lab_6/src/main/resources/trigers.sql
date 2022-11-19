use lab_6;


DROP TRIGGER IF EXISTS add_dysney_shop ;
DELIMITER //
CREATE TRIGGER add_dysney_shop
    BEFORE INSERT ON dysney_shop FOR EACH ROW
BEGIN
    DECLARE constraints INT;
    select count(*) into constraints from dysney_park where id = NEW.dysney_park_id;
    IF constraints = 0 THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'A foreign key constraint fails';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS update_dysney_shop ;

DELIMITER //
CREATE TRIGGER update_dysney_shop
    BEFORE UPDATE ON dysney_shop FOR EACH ROW
BEGIN
    DECLARE constraints INT;
    IF OLD.dysney_park_id <> NEW.dysney_park_id THEN
        select count(*) into constraints from dysney_park where id = NEW.dysney_park_id;
        IF constraints = 0 THEN
            SIGNAL SQLSTATE '22000'
                SET MESSAGE_TEXT = 'A foreign key constraint fails';
        END IF;
    END IF;
END //
DELIMITER ;


DROP TRIGGER IF EXISTS update_dysney_shop_id ;

DELIMITER //
CREATE TRIGGER update_dysney_shop_id
    BEFORE UPDATE ON dysney_park FOR EACH ROW
BEGIN
    DECLARE is_used_count INT;
    IF OLD.id <> NEW.id THEN
        select count(*) into is_used_count from dysney_shop where dysney_shop.dysney_park_id = OLD.id;
        IF is_used_count > 0 THEN
            SIGNAL SQLSTATE '22000'
                SET MESSAGE_TEXT = 'A foreign key constraint fails';
        END IF;
    END IF;
END //
DELIMITER ;


DROP TRIGGER IF EXISTS delete_dysney_shop ;

DELIMITER //
CREATE TRIGGER delete_dysney_shop
    BEFORE DELETE ON dysney_park FOR EACH ROW
BEGIN
    DECLARE is_used_count INT;
    select count(*) into is_used_count from dysney_shop where dysney_shop.dysney_park_id = OLD.id;
    IF is_used_count > 0 THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'A foreign key constraint fails';
    END IF;
END //
DELIMITER ;


DELIMITER //
DROP TRIGGER IF EXISTS forbidden_customers;
CREATE TRIGGER forbidden_customers
    BEFORE INSERT
    ON customer FOR EACH ROW
BEGIN
    IF NEW.first_name IN ('Frank', 'Georgius', 'Antonoromantyi', 'Taras') THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'Frank, Georgius, Antonoromantyi, Taras are not allowed';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS no_modification_for_dynsey_parks;
DELIMITER //
CREATE TRIGGER no_modification_for_dysney_parks
    BEFORE UPDATE
    ON dysney_park FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '22000'
        SET MESSAGE_TEXT = 'You can not update this table.';
END //
DELIMITER ;

DROP TRIGGER IF EXISTS no_delition_for_shows;
DELIMITER //
CREATE TRIGGER no_delition_for_shows
    BEFORE DELETE
    ON `show` FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '22000'
        SET MESSAGE_TEXT = 'NO delete';
END //
DELIMITER ;
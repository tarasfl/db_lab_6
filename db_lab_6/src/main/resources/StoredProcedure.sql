USE lab_6;

DROP PROCEDURE IF EXISTS insert_into_dysney_park;
DELIMITER //
CREATE PROCEDURE insert_into_dysney_park(
    IN city_p varchar(100),
    IN street_p varchar(100),
    IN max_amount_of_visitors_p INTEGER(10)
)
BEGIN
    INSERT INTO `dysney_park` (city, street, max_amount_of_visitors) VALUES (city_p, street_p, max_amount_of_visitors_p);
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS insert_into_employee_show;
DELIMITER //
CREATE PROCEDURE insert_into_employee_show(
    IN employee_first_name VARCHAR(50),
    IN show_name VARCHAR(50)
)
BEGIN
    DECLARE employee_id_val INT;
    DECLARE show_id_val INT;
    SELECT id INTO employee_id_val FROM employee WHERE first_name = employee_first_name LIMIT 1;
    SELECT id INTO show_id_val FROM `show` WHERE `name` = show_name LIMIT 1;
    IF employee_id_val is NULL or show_id_val is NULL THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'record not found';
    END IF;
    INSERT INTO employee_show (employee_id, show_id) VALUES
        (employee_id_val, show_id_val);
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS create_10_customers;
DELIMITER //
CREATE PROCEDURE create_10_customers()
BEGIN
    DECLARE i INT unsigned DEFAULT 0;
    START transaction;
    WHILE i < 10 DO
            INSERT INTO customer(first_name, second_name) VALUE (concat("NONAME", i), concat("NONAME", i));
            SET i = i + 1;
        END WHILE;
    COMMIT;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS avg_price;
DELIMITER //
CREATE FUNCTION avg_price()
    RETURNS INT DETERMINISTIC
BEGIN
    RETURN (SELECT AVG(price) FROM tickets);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS avg_ticket_price;
DELIMITER //
CREATE PROCEDURE avg_ticket_price(OUT avg_price INT)
BEGIN
    SELECT avg_price() INTO avg_price;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS create_random_customer_table;
DELIMITER //
CREATE PROCEDURE create_random_customer_table()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE n VARCHAR(50);
    DECLARE my_cursor CURSOR
        FOR SELECT first_name FROM `customer`;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN my_cursor;
    my_loop: LOOP
        FETCH my_cursor INTO n;
        IF (done = true) THEN LEAVE my_loop;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', n, DATE_FORMAT(NOW(), "_%Y_%m_%d_%H_%i_%s"), ' (', n, '1 INT, ', n, '2 BOOL);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //
DELIMITER ;
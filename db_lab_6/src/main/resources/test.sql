use lab_6;

INSERT INTO dysney_shop (max_amount_of_visitors, dysney_park_id)VALUES (13, 1);

UPDATE dysney_park SET max_amount_of_visitors = 2 WHERE id = 1;

DELETE FROM `show` WHERE id =1;

INSERT INTO customer (first_name, second_name, birthday) VALUES ('Taras', 'gh', NULL)
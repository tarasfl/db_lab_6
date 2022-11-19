
DROP DATABASE lab_6;
CREATE DATABASE IF NOT EXISTS lab_6;
USE lab_6;

DROP TABLE IF EXISTS `Show`;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS Attraction;
DROP TABLE IF EXISTS dysney_park;

CREATE TABLE Attraction (
                            id int NOT NULL AUTO_INCREMENT,
                            name varchar(100) NULL,
                            priority_pass bool NULL,
                            max_amount_of_visitors int NOT NULL,
                            dysney_park_id int NOT NULL,
                            CONSTRAINT Attraction_pk PRIMARY KEY (id,dysney_park_id)
)ENGINE = INNODB;


CREATE TABLE employee_show (
                            employee_id int NOT NULL,
                            show_id int NOT NULL,
                            CONSTRAINT Attraction_pk PRIMARY KEY (employee_id,show_id)
)ENGINE = INNODB;

-- Table: Show
CREATE TABLE `Show` (
                        name varchar(100) NULL,
                        id int NOT NULL AUTO_INCREMENT,
                        date date NOT NULL,
                        max_amount_of_visitors int NOT NULL,
                        dysney_park_id int NOT NULL,
                        CONSTRAINT Show_pk PRIMARY KEY (id,dysney_park_id)
)ENGINE = INNODB;

-- Table: customer
CREATE TABLE customer (
                          id int NOT NULL AUTO_INCREMENT,
                          first_name varchar(50) NOT NULL,
                          second_name varchar(50) NOT NULL,
                          birthday date NULL,
                          CONSTRAINT customer_pk PRIMARY KEY (id)
)ENGINE = INNODB;


CREATE TABLE dysney_shop (
                             id int NOT NULL AUTO_INCREMENT,
                             max_amount_of_visitors int,
                             dysney_park_id int NOT NULL,
                             CONSTRAINT tickets_pk PRIMARY KEY (id,dysney_park_id)
) ENGINE = INNODB;;


-- Table: dysney_park
CREATE TABLE dysney_park (
                             id int NOT NULL AUTO_INCREMENT,
                             city varchar(50) NOT NULL,
                             street varchar(125) NOT NULL,
                             max_amount_of_visitors int NULL,
                             CONSTRAINT dysney_park_pk PRIMARY KEY (id)
)ENGINE = INNODB;

-- Table: employee
CREATE TABLE employee (
                          id int NOT NULL AUTO_INCREMENT,
                          first_name varchar(50) NOT NULL,
                          second_name varchar(50) NOT NULL,
                          type_of_job char(50) NOT NULL,
                          Attraction_id int NOT NULL,
                          CONSTRAINT employee_pk PRIMARY KEY (id)
)ENGINE = INNODB;

-- Table: tickets
CREATE TABLE tickets (
                         id int NOT NULL AUTO_INCREMENT,
                         price decimal(6,2) NOT NULL,
                         priority_pass bool NOT NULL,
                         use_date date NOT NULL,
                         dysney_park_id int NOT NULL,
                         customer_id int NOT NULL,
                         CONSTRAINT tickets_pk PRIMARY KEY (id,dysney_park_id,customer_id)
)ENGINE = INNODB;


CREATE INDEX name_index ON Attraction (`name`);
CREATE INDEX name_index ON customer (`first_name`);
-- foreign keys
-- Reference: Attraction_dysney_park (table: Attraction)
ALTER TABLE Attraction ADD CONSTRAINT Attraction_dysney_park FOREIGN KEY Attraction_dysney_park (dysney_park_id)
    REFERENCES dysney_park (id);


-- Reference: Show_dysney_park (table: Show)
ALTER TABLE `Show` ADD CONSTRAINT Show_dysney_park FOREIGN KEY Show_dysney_park (dysney_park_id)
    REFERENCES dysney_park (id);

-- Reference: employee_Attraction (table: employee)
ALTER TABLE employee ADD CONSTRAINT employee_Attraction FOREIGN KEY employee_Attraction (Attraction_id)
    REFERENCES Attraction (id);

-- Reference: tickets_customer (table: tickets)
ALTER TABLE tickets ADD CONSTRAINT tickets_customer FOREIGN KEY tickets_customer (customer_id)
    REFERENCES customer (id);

-- Reference: tickets_dysney_park (table: tickets)
ALTER TABLE tickets ADD CONSTRAINT tickets_dysney_park FOREIGN KEY tickets_dysney_park (dysney_park_id)
    REFERENCES dysney_park (id);



INSERT INTO dysney_park (city, street, max_amount_of_visitors)VALUES
                                                                  ('Lviv', 'shevchenks', 7),
                                                                  ('Kyiv', 'franka', 5),
                                                                  ('Ternopil', 'Bandery', 13),
                                                                  ('Ivano Frankivsk', 'shevchenks', 11),
                                                                  ('New York', 'white', 13),
                                                                  ('LA', 'yellow', 4),
                                                                  ('Toronto', 'green', 8),
                                                                  ('G', 'grey', 1),
                                                                  ('Huhua', 'huhua', 9),
                                                                  ('Leggoe', 'black', 6),
                                                                  ('Lviv', 'franka', 8);

INSERT INTO customer (first_name, second_name, birthday)VALUES
                                                            ('petro', 'petrenko', '1900-7-12'),
                                                            ('Igor', 'petrenko', '1999-9-9'),
                                                            ('Marko', 'markovych', '1102-12-9'),
                                                            ('Ivan', 'petrenko', '1991-12-1'),
                                                            ('Vanya', 'jujxsd', '2300-7-11'),
                                                            ('Laous', 'yellow', '1000-1-1'),
                                                            ('Toronto', 'green', '1900-12-6'),
                                                            ('Gojo', 'grey', '1780-5-11'),
                                                            ('Max', 'Mychal', '2720-5-11'),
                                                            ('Anton', 'Tonon', '1900-7-12');

INSERT INTO `attraction` (`name`, `priority_pass`, `max_amount_of_visitors`, `dysney_park_id`)
VALUES('Igor', 1, 5, 1),
      ('Karusel', 0, 3, 3),
      ('Slope', 1, 12, 2),
      ('Roler Coster', 1, 14, 7),
      ('Slope', 0, 3, 9),
      ('Roler Coster', 1, 6, 6),
      ('Igor', 1, 5, 4),
      ('Karusel', 0, 7, 8),
      ('Slope', 1, 5, 3),
      ('Roler Coster', 1, 8, 2);

INSERT INTO `show` (`name`, date, `max_amount_of_visitors`, `dysney_park_id`)
VALUES('music', '1900-7-12', 5, 1),
      ('festival', '1900-7-12', 3, 3),
      ('music', '1900-7-12', 12, 2),
      ('concert', '1900-7-12', 14, 7),
      ('theater', '1900-7-12', 3, 9),
      ('festival', '1900-7-12', 6, 6),
      ('music', '1900-7-12', 5, 4),
      ('concert', '1900-7-12', 7, 8),
      ('theater', '1900-7-12', 5, 3),
      ('festivalr', '1900-7-12', 8, 2);

INSERT INTO employee (first_name, second_name, type_of_job, attraction_id)VALUES
                                                                              ('petro', 'petrenko', 'manager', 3),
                                                                              ('Igor', 'petrenko', 'cleaner', 2),
                                                                              ('Marko', 'markovych', 'guard', 1),
                                                                              ('Ivan', 'petrenko', 'attraction master', 6),
                                                                              ('Ivan', 'jujxsd', 'cleaner', 5),
                                                                              ('Laous', 'yellow', 'guard', 4),
                                                                              ('Toronto', 'green', 'cleaner', 10),
                                                                              ('Gojo', 'grey', 'guard', 7),
                                                                              ('Igor', 'Mychal', 'manager', 8),
                                                                              ('Anton', 'Tonon', 'manager', 9);

INSERT INTO tickets (price, priority_pass, use_date, dysney_park_id, customer_id)VALUES
                                                                                     (13.4, 1, '1999-9-9', 3, 5),
                                                                                     (1.2, 0, '1979-9-19', 2, 4),
                                                                                     (4.5, 1, '2000-9-9', 1, 3),
                                                                                     (13.2, 0, '2001-9-13', 6, 2),
                                                                                     (2, 1, '1402-9-19', 5, 1),
                                                                                     (4, 0, '1999-9-9', 4, 10),
                                                                                     (8.1, 1, '2012-9-9', 10, 9),
                                                                                     (9, 0, '1999-9-13', 7, 8),
                                                                                     (0.42, 1, '1978-9-9', 8, 7),
                                                                                     (7, 0, '1999-9-9', 9, 6);



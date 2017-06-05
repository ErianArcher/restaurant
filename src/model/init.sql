CREATE TABLE IF NOT EXISTS customer (
    id INT NOT NULL auto_increment,
    name VARCHAR(30) NOT NULL,
    phone VARCHAR(16) default NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS deliveryboy (
    id INT NOT NULL auto_increment,
    area_code INT NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS food (
    id INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(3,2) default 0.0,
    type VARCHAR(10) default NULL,
    introduction TEXT default NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS `orders` (
    id INT NOT NULL auto_increment,
    delivery_boy_id INT DEFAULT -1,
    customer_id INT NOT NULL DEFAULT -1,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS orderdetail (
        id INT NOT NULL auto_increment,
        food_id INT NOT NULL DEFAULT -1,
        order_id INT NOT NULL DEFAULT -1,
        quantity INT default 0 check(quantity>0),
        PRIMARY KEY (id)
        );
USE my_demo
CREATE TABLE IF NOT EXISTS users (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    accountId varchar(20) NOT NULL,
    password varchar(20) NOT NULL
);
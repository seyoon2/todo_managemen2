USE my_demo
CREATE TABLE IF NOT EXISTS 'users' (
    'id' int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    'account_id' varchar(20) NOT NULL,
    'password' char(60) NOT NULL
);
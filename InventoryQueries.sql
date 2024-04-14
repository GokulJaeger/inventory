create database inventory;

use inventory;

CREATE TABLE users (
id INT AUTO_INCREMENT PRIMARY KEY,
userId VARCHAR(50) UNIQUE NOT NULL,
fullName VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
jwtToken VARCHAR(255),
platform VARCHAR(100),
deviceId VARCHAR(100),
roleId INT NOT NULL,
userLogin INT,
isActive BOOLEAN NOT NULL,
createdAt DATETIME,
updatedAt DATETIME
);


CREATE TABLE roles (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) UNIQUE NOT NULL,
description VARCHAR(100) NOT NULL,
createdAt DATETIME,
updatedAt DATETIME
);

CREATE TABLE userLogin(
id INT AUTO_INCREMENT PRIMARY KEY,
userId INT NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
accessToken VARCHAR(255) NOT NULL,
loggedAt DATETIME,
FOREIGN KEY (userId) REFERENCES users(id)
);

insert into roles(name,description) VALUES("ADMIN","Administrator Access Control"),("USER","Customer Access Control");

insert into userLogin(email,userId,accessToken) values("administrator@gmail.com", 1,"empty_token"),("krishnaprabha@gmail.com", 2,"empty_token"),("guest@gmail.com", 3,"empty_token"),("guest2@gmail.com", 4,"empty_token"),("gokulr@gmail.com", 7,"empty_token");

update users set userLogin=1 where id = 1;
update users set userLogin=2 where id = 2;
update users set userLogin=3 where id = 3;
update users set userLogin=4 where id = 4;
update users set userLogin=5 where id = 7;
update users set userLogin=6 where id = 8;
update users set userLogin=7 where id = 9;
update users set userLogin=8 where id = 10;
update users set userLogin=9 where id = 11;


alter table users modify column createdAt DATETIME;
alter table users modify column updatedAt DATETIME;
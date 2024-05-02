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

CREATE TABLE product(
id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(255) UNIQUE NOT NULL,
cost varchar(255) NOT NULL,
barcode varchar(255) NOT NULL,
quantity varchar(255) NOT NULL,
brand varchar(255) NOT NULL,
description varchar(255) NOT NULL,
location varchar(255) NOT NULL,
image varchar(255) NOT NULL,
utilId int NOT NULL,
createdAt DATETIME NOT NULL,
updatedAt DATETIME NOT NULL,
isActive BOOLEAN NOT NULL,
FOREIGN KEY (utilId) REFERENCES utils(id)
);

CREATE TABLE utils (
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    createdAt DATETIME,
	updatedAt DATETIME,
    isActive BOOLEAN NOT NULL
);

insert into roles(name,description) VALUES("ADMIN","Administrator Access Control"),("USER","Customer Access Control");

insert into userLogin(email,userId,accessToken) values("administrator@gmail.com", 1,"empty_token"),("krishnaprabha@gmail.com", 2,"empty_token"),("guest@gmail.com", 3,"empty_token"),("guest2@gmail.com", 4,"empty_token"),("gokulr@gmail.com", 7,"empty_token");

insert into product(name, cost, barcode, quantity, brand, description, location, image, utilId, createdAt, updatedAt, isActive) values("New i20", "1500000", "HG8765JHBJG5", "5 units", "HYUNDAI", "ABC123", "KOREA", "i20image.jpg",1, "2024-04-30 00:50:17", "2024-04-30 00:50:17", true);
insert into product(name, cost, barcode, quantity, brand, description, location, image, utilId, createdAt, updatedAt, isActive) values("Verna", "2100000", "IOW887KJ78756GH", "15 units", "HYUNDAI", "ABC4567", "KOREA", "vernaimage.jpg",1, "2024-04-30 00:50:17", "2024-04-30 00:50:17", true);

insert into utils(name, createdAt, updatedAt, isActive ) values("Car", "2024-04-29 21:54:17", "2024-04-29 21:54:17", true);
insert into utils(name, createdAt, updatedAt, isActive ) values("Bike", "2024-04-29 21:54:45", "2024-04-29 21:54:45", true);
insert into utils(name, createdAt, updatedAt, isActive ) values("Cycle", "2024-04-29 21:54:45", "2024-04-29 21:54:45", true);


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

alter table product add column isActive BOOLEAN NOT NULL;
alter table utils add column isActive BOOLEAN NOT NULL;






DROP DATABASE IF EXISTS abdb;

CREATE DATABASE abdb;

USE abdb;
-- initializing some roles
INSERT INTO Role (roleName) VALUES ('Manager');
INSERT INTO Role (roleName) VALUES ('Supervisor');
INSERT INTO Role (roleName) VALUES ('Post Processing Clerk');
INSERT INTO Role (roleName) VALUES ('Credit Broker');

-- and some staff
INSERT INTO Staff (staffId, firstName, lastName, password)
VALUES ('gerald', 'Gerald', 'Manager', 'abcd1234');

INSERT INTO Staff (staffId, firstName, lastName, password)
VALUES ('james', 'James', 'Supervisor', 'abcd1234');

INSERT INTO Staff (staffId, firstName, lastName, password)
VALUES ('david', 'David', 'Clerk', 'abcd1234');

INSERT INTO Staff (staffId, firstName, lastName, password)
VALUES ('bryan', 'Bryan', 'Broker', 'abcd1234');

INSERT INTO Customer (firstName, lastName, pin, income, email)
VALUES ('Alice', 'Power', 'abcd1234', '80000', 'power@abc.com');

CREATE TABLE contacts(
  cid INT PRIMARY KEY,
  cname VARCHAR(20) NOT NULL,
  grp VARCHAR(20) NOT NULL,
  dob DATE NOT NULL,
  mobile CHAR(10) NOT NULL UNIQUE
);

INSERT INTO contacts VALUES
 (101,'Vamsy','FAMILY','1985-06-11','9052224753'),
 (102,'Sagar','FAMILY','1985-07-11','9052224755'),
 (103,'Srinu','WORK','1985-08-11','9052224756'),
 (104,'Venky','WORK','1985-09-11','9052224757'),
 (105,'Indu','FAMILY','1986-06-11','9052224758');

 
 CREATE TABLE adb_users(
 	user_name varchar(20) primary key,
 	password varchar(20) not null,
 	role varchar(10) not null
 ); 
 
 INSERT INTO adb_users VALUES
 	('admin','admin','ADMIN');
 
 commit;
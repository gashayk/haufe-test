CREATE TABLE IF NOT EXISTS PROVIDERS (
     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
     NAME VARCHAR(50) NULL,
     COUNTRY VARCHAR(50) NULL,
     CREATION_DATE TIMESTAMP NULL,
     CREATED_BY VARCHAR(50) NULL,
     MODIFICATION_DATE TIMESTAMP NULL,
     MODIFIED_BY VARCHAR(50) NULL);

CREATE TABLE IF NOT EXISTS BEERS (
     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
     PROVIDER_ID BIGINT NULL,
     NAME VARCHAR(50) NULL,
     GRADUATION VARCHAR(10) NULL,
     TYPE VARCHAR(10) NULL,
     DESCRIPTION VARCHAR(300) NULL,
     CREATION_DATE TIMESTAMP NULL,
     CREATED_BY VARCHAR(50) NULL,
     MODIFICATION_DATE TIMESTAMP NULL,
     MODIFIED_BY VARCHAR(50) NULL,
     FOREIGN KEY (PROVIDER_ID) REFERENCES PROVIDERS(ID));

CREATE TABLE IF NOT EXISTS USERS (
     USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
     USERNAME VARCHAR(45) NOT NULL,
     PASSWORD VARCHAR(64) NOT NULL);

CREATE TABLE IF NOT EXISTS ROLES (
     ROLE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
     NAME VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS USERS_ROLES (
   USER_ID BIGINT NOT NULL,
   ROLE_ID BIGINT NOT NULL,
   FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),
   FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ROLE_ID));

COMMIT;
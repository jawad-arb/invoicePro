CREATE SCHEMA IF NOT EXISTS invoicepro;

SET NAMES 'UTF8MB4';


USE invoicepro;


-- Now drop the 'users' table if it exists
DROP TABLE IF EXISTS users;


CREATE TABLE users(
                      id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(50) NOT NULL,
                      last_name  VARCHAR(50) NOT NULL,
                      email      VARCHAR(100) NOT NULL,
                      password   VARCHAR(255) DEFAULT NULL,
                      address    VARCHAR(255) DEFAULT NULL,
                      phone      VARCHAR(30) DEFAULT NULL,
                      title      VARCHAR(50) DEFAULT NULL,
                      bio        VARCHAR(255) DEFAULT NULL,
                      enabled    BOOLEAN DEFAULT FALSE,
                      non_locked BOOLEAN DEFAULT TRUE,
                      using_mfa  BOOLEAN DEFAULT FALSE,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      image_url  VARCHAR(255) DEFAULT 'images/images.jpeg',
                      CONSTRAINT UQ_Users_Email UNIQUE (email)


);



DROP TABLE IF EXISTS roles;

CREATE TABLE roles(
                      id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name        VARCHAR(50) NOT NULL,
                      permission  VARCHAR(50) NOT NULL,
                      CONSTRAINT UQ_Roles_Name UNIQUE(name)
);



DROP TABLE IF EXISTS UserRoles;


CREATE TABLE UserRoles(
                          id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          user_id     BIGINT UNSIGNED NOT NULL,
                          role_id     BIGINT UNSIGNED NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE ,
                          FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE ,
                          CONSTRAINT UQ_UserRoles_User_Id UNIQUE (user_id)
);

DROP TABLE IF EXISTS events;

CREATE TABLE events(
                       id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       type VARCHAR(50) NOT NULL CHECK ( type IN ('LOGIN_ATTEMPT',
                                                                  'LOGIN_ATTEMPT_FAILURE',
                                                                  'LOGIN_ATTEMPT_SUCCESS',
                                                                  'PROFILE_UPDATE',
                                                                  'PROFILE_PICTURE_UPDATE',
                                                                  'ROLE_UPDATE',
                                                                  'ACCOUNT_SETTINGS_UPDATE',
                                                                  'PASSWORD_UPDATE',
                                                                  'MFA_UPDATE')),
                       description VARCHAR(255) NOT NULL,
                       CONSTRAINT UQ_Events_Type UNIQUE (type)
);

DROP TABLE IF EXISTS UserEvents;
CREATE TABLE UserEvents(
                           id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT UNSIGNED NOT NULL,
                           event_id BIGINT UNSIGNED NOT NULL,
                           device VARCHAR(100) DEFAULT NULL,
                           ip_address VARCHAR(100) DEFAULT NULL,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE ,
                           FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE ON UPDATE CASCADE ,


);


DROP TABLE IF EXISTS AccountVerifications;
CREATE TABLE AccountVerifications(
                           id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT UNSIGNED NOT NULL,
                           url VARCHAR(255) NOT NULL,
--                         date DATETIME NOT NULL ,
                           FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE ,
                           CONSTRAINT UQ_AccountVerifications_User UNIQUE (user_id),
                           CONSTRAINT UQ_AccountVerifications_url UNIQUE (url)


);

DROP TABLE IF EXISTS ResetPasswordVerifications;
CREATE TABLE AccountVerifications(
                           id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT UNSIGNED NOT NULL,
                           url VARCHAR(255) NOT NULL,
                           expiration_date DATETIME NOT NULL ,
                           FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE ,
                           CONSTRAINT UQ_ResetPasswordVerifications_User UNIQUE (user_id),
                           CONSTRAINT UQ_ResetPasswordVerifications_url UNIQUE (url)


);

DROP TABLE IF EXISTS TwoFactorVerifications;
CREATE TABLE TwoFactorVerifications(
                           id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT UNSIGNED NOT NULL,
                           code VARCHAR(10) NOT NULL,
                           expiration_date DATETIME NOT NULL ,
                           FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE ,
                           CONSTRAINT UQ_TwoFactorVerifications_User UNIQUE (user_id),
                           CONSTRAINT UQ_TwoFactorVerifications_code UNIQUE (code)


);





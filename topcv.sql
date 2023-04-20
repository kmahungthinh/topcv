CREATE DATABASE IF NOT EXISTS topcv;
USE topcv;
CREATE TABLE IF NOT EXISTS users
(
    `id`          INT            NOT NULL AUTO_INCREMENT,
    `avatar`      TEXT           NOT NULL,
    `name`        NVARCHAR(255)  NOT NULL,
    `sex`         NVARCHAR(10)   NOT NULL,
    `dob`         DATE           NOT NULL,
    `email`       NVARCHAR(320)  NOT NULL,
    `password`    VARBINARY(500) NOT NULL,
    `phone`       NVARCHAR(20)   NOT NULL,
    `address`     TEXT           NOT NULL,
    `verify_code` VARCHAR(100)   NOT NULL,
    `state`       VARCHAR(20)    NOT NULL,
    `create_date` DATE           NOT NULL,
    PRIMARY KEY (id)
);
CREATE INDEX idx_user ON users (id);

CREATE TABLE IF NOT EXISTS companies
(
    `id`          INT AUTO_INCREMENT,
    `avatar`      TEXT           NOT NULL,
    `name`        NVARCHAR(255)  NOT NULL,
    `email`       NVARCHAR(320)  NOT NULL,
    `password`    VARBINARY(500) NOT NULL,
    `phone`       NVARCHAR(20)   NOT NULL,
    `address`     TEXT           NOT NULL,
    `introduce`   TEXT           NOT NULL,
    `verify_code` VARCHAR(100)   NOT NULL,
    `state`       VARCHAR(20)    NOT NULL,
    `create_date` DATE           NOT NULL,
    PRIMARY KEY (id)
);
CREATE INDEX idx_company ON companies (id);

CREATE TABLE IF NOT EXISTS jobs
(
    `id`             INT           NOT NULL AUTO_INCREMENT,
    `name`           NVARCHAR(255) NOT NULL,
    `salary`         NVARCHAR(100) NOT NULL,
    `number_recruit` INT           NOT NULL,
    `working_form`   NVARCHAR(100) NOT NULL,
    `level`          NVARCHAR(100) NOT NULL,
    `gender`         NVARCHAR(10),
    `experience`     INT           NOT NULL,
    `content`        TEXT          NOT NULL,
    `state`          VARCHAR(20)   NOT NULL,
    `company_id`     INT,
    `create_date`    DATE          NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_Job_CompanyId FOREIGN KEY (company_id)
        REFERENCES companies (id)
        ON DELETE CASCADE
);
CREATE INDEX idx_job ON jobs (id);

CREATE TABLE IF NOT EXISTS followings
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `user_id`    INT NOT NULL,
    `company_id` INT,
    `job_id`     INT,
    PRIMARY KEY (id),
    CONSTRAINT FK_Following_UserId FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE,
    CONSTRAINT FK_Following_CompanyId FOREIGN KEY (company_id)
        REFERENCES companies (id)
        ON DELETE CASCADE,
    CONSTRAINT FK_Following_JobId FOREIGN KEY (job_id)
        REFERENCES jobs (id)
        ON DELETE CASCADE
);
CREATE INDEX idx_following ON followings (id);

CREATE TABLE IF NOT EXISTS applies
(
    `user_id` INT NOT NULL,
    `job_id`  INT NOT NULL,
    PRIMARY KEY (user_id, job_id),
    CONSTRAINT FK_Apply_UserId FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE,
    CONSTRAINT FK_Apply_JobId FOREIGN KEY (job_id)
        REFERENCES jobs (id)
        ON DELETE CASCADE
);
CREATE INDEX idx_apply ON applies (user_id, job_id);

CREATE TABLE IF NOT EXISTS files
(
    `id`          INT           NOT NULL AUTO_INCREMENT,
    `name`        NVARCHAR(255) NOT NULL,
    `url`         TEXT          NOT NULL,
    `state`       VARCHAR(20)   NOT NULL,
    `user_id`     INT           NOT NULL,
    `create_date` DATE          NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_File_UserId FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);
CREATE INDEX idx_file ON files (id);
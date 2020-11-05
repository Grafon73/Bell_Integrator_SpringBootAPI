CREATE TABLE IF NOT EXISTS Country (
code       INTEGER NOT NULL       COMMENT 'Код страны' PRIMARY KEY,
version    INTEGER NOT NULL       COMMENT 'Служебное поле hibernate',
name       VARCHAR(50) NOT NULL   COMMENT 'Название страны'
);
COMMENT ON TABLE Country IS 'Страна';


CREATE TABLE IF NOT EXISTS Doc (
code       INTEGER     NOT NULL   COMMENT 'Код страны' PRIMARY KEY,
version    INTEGER     NOT NULL   COMMENT 'Служебное поле hibernate',
name       VARCHAR(255) NOT NULL   COMMENT 'Название документа'
);
COMMENT ON TABLE Doc IS 'Документ';



CREATE TABLE IF NOT EXISTS User_doc (
    id              INTEGER                 COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER     NOT NULL    COMMENT 'Служебное поле hibernate',
    doc_number      VARCHAR(20) NOT NULL    COMMENT 'Номер документа',
    doc_date        DATE        NOT NULL    COMMENT 'Дата выдачи документа',
    doc_code        INTEGER     NOT NULL    COMMENT 'Код документа'
);
COMMENT ON TABLE User_doc IS 'Документ юзера';

CREATE INDEX IX_User_doc_doc_code ON User_doc (doc_code);
ALTER TABLE User_doc ADD FOREIGN KEY (doc_code) REFERENCES Doc(code);




CREATE TABLE IF NOT EXISTS Organization (
id         INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
version    INTEGER NOT NULL            COMMENT 'Служебное поле hibernate',
name       VARCHAR(50) NOT NULL        COMMENT 'Название организации',
full_name  VARCHAR(255) NOT NULL        COMMENT 'Полное название организации',
inn        VARCHAR(50) NOT NULL        COMMENT 'ИНН организации',
kpp        VARCHAR(50) NOT NULL        COMMENT 'КПП организации',
address    VARCHAR(255) NOT NULL        COMMENT 'Адрес организации',
phone      VARCHAR(20)                 COMMENT 'Телефон организации',
is_active  BOOLEAN                     COMMENT 'Статус организации'
);


CREATE TABLE IF NOT EXISTS office (
id         INTEGER                      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
version    INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
org_id     INTEGER NOT NULL             COMMENT 'Идентификатор Организации',
name       VARCHAR(50) NOT NULL         COMMENT 'Название офиса',
address    VARCHAR(255) NOT NULL         COMMENT 'Адрес офиса',
phone      VARCHAR(20)                  COMMENT 'Телефон офиса',
is_active  BOOLEAN                      COMMENT 'Статус офиса'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE INDEX IX_Office_org_id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);


CREATE TABLE IF NOT EXISTS user (
id            INTEGER                      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
version       INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
office_id     INTEGER NOT NULL             COMMENT 'Идентификатор Офиса',
first_name    VARCHAR(50) NOT NULL         COMMENT 'Имя юзера',
second_name   VARCHAR(50)                  COMMENT 'Фамилия юзера',
middle_name   VARCHAR(50)                  COMMENT 'Среднее имя юзера',
position      VARCHAR(50) NOT NULL         COMMENT 'Должность юзера',
phone         VARCHAR(20)                  COMMENT 'Телефон юзера',
doc_id        INTEGER                      COMMENT 'id документа юзера',
citizenship   INTEGER                      COMMENT 'Код страны юзера',
is_identified BOOLEAN                      COMMENT 'Статус идентификации'
);

COMMENT ON TABLE User IS 'Юзер';

CREATE INDEX IX_User_office_id   ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id)   REFERENCES Office(id);

CREATE UNIQUE INDEX UX_User_doc_id ON User (doc_id);
ALTER TABLE User ADD FOREIGN KEY (doc_id) REFERENCES User_doc(id);

CREATE INDEX IX_User_citizenship ON User (citizenship);
ALTER TABLE User ADD FOREIGN KEY (citizenship) REFERENCES Country(code);

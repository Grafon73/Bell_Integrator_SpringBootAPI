CREATE TABLE IF NOT EXISTS country (
code       INTEGER NOT NULL UNIQUE      COMMENT 'Код страны',
name       VARCHAR(50) NOT NULL UNIQUE  COMMENT 'Название страны',
version    INTEGER NOT NULL            COMMENT 'Служебное поле hibernate',

PRIMARY KEY (code)
);
COMMENT ON TABLE country IS 'Страна';

CREATE TABLE IF NOT EXISTS doc (
code       INTEGER NOT NULL UNIQUE      COMMENT 'Код документа',
name       VARCHAR(50) NOT NULL UNIQUE  COMMENT 'Название документа',
version    INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',

PRIMARY KEY (code)
);
COMMENT ON TABLE doc IS 'Документ';


CREATE TABLE IF NOT EXISTS organization (
id         INTEGER                     COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
version    INTEGER NOT NULL            COMMENT 'Служебное поле hibernate',
name       VARCHAR(50) NOT NULL        COMMENT 'Название организации',
full_name  VARCHAR(50) NOT NULL        COMMENT 'Полное название организации',
inn        VARCHAR(50) NOT NULL        COMMENT 'ИНН организации',
kpp        VARCHAR(50) NOT NULL        COMMENT 'КПП организации',
address    VARCHAR(50) NOT NULL        COMMENT 'Адрес организации',
phone      VARCHAR(20)                 COMMENT 'Телефон организации',
is_active  TINYINT NOT NULL DEFAULT 1  COMMENT 'Статус организации'

);
COMMENT ON TABLE organization IS 'Организация';
CREATE INDEX IX_organization_name ON organization (name);

CREATE TABLE IF NOT EXISTS office (
id         INTEGER                      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
version    INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
org_id     INTEGER NOT NULL             COMMENT 'Идентификатор Организации',
name       VARCHAR(50) NOT NULL         COMMENT 'Название офиса',
address    VARCHAR(50) NOT NULL         COMMENT 'Адрес офиса',
phone      VARCHAR(20)                  COMMENT 'Телефон офиса',
is_active  TINYINT NOT NULL DEFAULT 1   COMMENT 'Статус офиса',

FOREIGN KEY (org_id) REFERENCES organization (id)
);
COMMENT ON TABLE office IS 'Офис';
CREATE INDEX IX_office_org_id ON office (org_id);

CREATE TABLE IF NOT EXISTS user (
id          INTEGER                      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
version     INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
office_id   INTEGER NOT NULL             COMMENT 'Идентификатор Офиса',
first_name  VARCHAR(50) NOT NULL         COMMENT 'Имя юзера',
second_name VARCHAR(50)                  COMMENT 'Фамилия юзера',
middle_name VARCHAR(50)                  COMMENT 'Среднее имя юзера',
position    VARCHAR(50) NOT NULL         COMMENT 'Должность юзера',
phone       VARCHAR(20)                  COMMENT 'Телефон юзера',
doc_code    INTEGER                      COMMENT 'Номер документа юзера',
doc_name    VARCHAR(50)                  COMMENT 'Документ юзера',
doc_number  VARCHAR(50)                  COMMENT 'Номер документа юзера',
doc_date    DATE                         COMMENT 'Дата регистрации документа юзера',
citizenship_code INTEGER                 COMMENT 'Код страны юзера',
citizenship_name VARCHAR(50)             COMMENT 'Название страны юзера',
is_identified TINYINT NOT NULL DEFAULT 1 COMMENT 'Статус идентификации',


CONSTRAINT IX_user_office_id FOREIGN KEY (office_id) REFERENCES office (id),
CONSTRAINT IX_user_doc_code FOREIGN KEY (doc_code) REFERENCES doc (code),
CONSTRAINT IX_user_citizenship_code FOREIGN KEY (citizenship_code) REFERENCES country (code)
);
COMMENT ON TABLE user IS 'Юзер';
CREATE INDEX IX_user_office_id ON user (office_id);


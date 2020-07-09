drop table postgres.PATIENT;
drop table postgres.PATIENT_VACCINATION;
drop table postgres.VACCINATION;
drop SEQUENCE postgres.patients_id_seq;
drop SEQUENCE postgres.vaccines_id_seq;

CREATE TABLE IF NOT EXISTS postgres.PATIENT
(
    ID               BIGSERIAL PRIMARY KEY,
    LASTNAME         VARCHAR(255) NOT NULL,
    FIRSTNAME        VARCHAR(255) NOT NULL,
    MIDDLENAME       VARCHAR(255),
    BIRTH_DATE       DATE         NOT NULL,
    GENDER           VARCHAR(255) NOT NULL,
    INSURANCE_NUMBER VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS postgres.PATIENT_VACCINATION
(
    PATIENT_ID     integer NOT NULL,
    VACCINATION_ID integer NOT NULL
);

CREATE TABLE IF NOT EXISTS postgres.VACCINATION
(
    ID               BIGSERIAL PRIMARY KEY,
    VACCINE          VARCHAR(255) NOT NULL,
    PERMISSION       varchar(1)   NOT NULL,
    VACCINATION_DATE DATE         NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS postgres.vaccines_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS postgres.patients_id_seq START WITH 1 INCREMENT BY 1;

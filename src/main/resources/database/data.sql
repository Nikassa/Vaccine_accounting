insert into postgres.PATIENT(id, lastname, firstname, middlename, birth_date, gender, insurance_number)
values (1, 'Иванов', 'Иван', 'Иванович', '15-JUL-1990', 'male', '160-722-773 54'),
       (2, 'Петров', 'Петр', 'Петрович', '14-JUN-1980', 'male', '160-722-773 54');

insert into postgres.PATIENT_VACCINATION
values (1, 1),
       (2, 1),
       (2, 2);

insert into postgres.vaccination
values (1, 'АКДС', 'Y', '2017-04-30'),
       (2, 'АКДС', 'Y', '2017-04-30');

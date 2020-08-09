package ru.my.task.vaccine_accounting.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.my.task.vaccine_accounting.model.Patient;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientRepositoryTest extends ConfigIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @Transactional
    public void updatePatientNameById() {
        insertUsers();
        int updatedUsersSize = patientRepository.updatePatientNameById(3);
        assertThat(updatedUsersSize).isEqualTo(1);
    }

    private void insertUsers() {
        patientRepository.save(new Patient(null, "Первый", "Федор", "Федорович",
                new Date("14-JUN-80"), "male", "160-722-773 54"));
        patientRepository.save(new Patient(null, "Второй", "Федор", "Федорович",
                new Date("14-JUN-80"), "male", "160-722-773 54"));
        patientRepository.save(new Patient(null, "Третий", "Федор", "Федорович",
                new Date("14-JUN-80"), "male", "160-722-773 54"));
        patientRepository.save(new Patient(null, "Четвертый", "Федор", "Федорович",
                new Date("14-JUN-80"), "male", "160-722-773 54"));
        patientRepository.flush();
    }
}
//package ru.my.task.vaccine_accounting.repository;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//import ru.my.task.vaccine_accounting.model.Patient;
//import ru.my.task.vaccine_accounting.model.Vaccination;
//
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class VaccinationRepositoryIntegrationTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private VaccinationRepository vaccinationRepository;
//
//    @Test
//    public void whenFindByName_thenReturnEmployee() {
//
////        public Vaccination(String vaccine, String permission, Date vaccinationDate, List<Patient> patients) {
////        Vaccination vaccine = new Vaccination("АКДС", "Y", new Date("15.03.2020"), (List<Patient>) new Patient());
////        entityManager.persist(vaccine);
////        entityManager.flush();
////
////        Vaccination found = vaccinationRepository.read(1);
////        assertThat(found.getVaccine()).isEqual(vaccine.getVaccine());
//
//
//
//
//        // given
////        Employee alex = new Employee("alex");
////        entityManager.persist(alex);
////        entityManager.flush();
//
//        // when
////        Employee found = employeeRepository.findByName(alex.getName());
//
//        // then
////        assertThat(found.getName())
////                .isEqualTo(alex.getName());
//    }
//}
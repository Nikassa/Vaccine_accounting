package ru.my.task.vaccine_accounting.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.my.task.vaccine_accounting.VaccineAccountingApplication;
import ru.my.task.vaccine_accounting.model.Patient;
import ru.my.task.vaccine_accounting.repository.PatientRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = VaccineAccountingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "/application-integrationtest.properties")
@Sql(value = {"/create-patient-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/create-patient-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PatientControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository repository;

    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void shouldBeEqualsInsuranceNumberWhenEqualsPatientId()
            throws Exception {

        Patient patient = new Patient();
        patient.setId(1);
        patient.setInsuranceNumber("160-722-773-54");

        mvc.perform(get("/api/patients/1/vaccinations"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.insuranceNumber").value(patient.getInsuranceNumber()));
    }


}
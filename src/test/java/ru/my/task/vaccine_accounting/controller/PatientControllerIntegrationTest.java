package ru.my.task.vaccine_accounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.my.task.vaccine_accounting.VaccineAccountingApplication;
import ru.my.task.vaccine_accounting.model.Patient;
import ru.my.task.vaccine_accounting.repository.PatientRepository;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = VaccineAccountingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "/application-integrationtest.properties")
//@Sql(value = {"/create-patient-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/create-patient-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PatientControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository repository;

    @Test
    public void whenEqualsPatientId_thenEqualsInsuranceNumber() throws Exception {

        Patient patient = new Patient();
        patient.setId(1);
        patient.setInsuranceNumber("160-722-773 54");

        mvc.perform(get("/api/patients/1/vaccinations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.insuranceNumber").value(patient.getInsuranceNumber()));
    }

    @Test
    public void whenGetListOfPatients_thenListContainsPatients() throws Exception {

        mvc.perform(get("/api/patients"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]").exists())
                .andExpect(jsonPath("$[*]['id']").isNotEmpty());
    }

    @Test
    public void whenCreatePatient_thenExistsPatient() throws Exception {

        Patient patient = new Patient(null, "Федоров", "Федор", "Федорович",
                new Date("14-JUN-80"), "male", "160-722-773 54");

        mvc.perform(post("/patients")
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[*]").exists());
    }

    @Test
    public void whenUpdatePatient_thenEqualsData() throws Exception {

        Patient patient = new Patient(null, "Федоров", "Федор", "Федорович",
                new Date("14-JUN-80"), "female", "160-722-773 54");

        mvc.perform(put("/patients/{id}", 3)
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(patient.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()))
                .andExpect(jsonPath("$.insuranceNumber").value(patient.getInsuranceNumber()));
    }

    @Test
    public void whenDeletePatient_thenReturnStatusAccepted() throws Exception {
        mvc.perform(delete("/api/patients/{id}", 3))
                .andExpect(status().isAccepted());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
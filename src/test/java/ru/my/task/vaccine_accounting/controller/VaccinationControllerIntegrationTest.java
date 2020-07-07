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
import ru.my.task.vaccine_accounting.model.Vaccination;
import ru.my.task.vaccine_accounting.repository.VaccinationRepository;

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
public class VaccinationControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VaccinationRepository repository;

    @Test
    public void whenEqualsVaccinationId_thenEqualsInsuranceNumber() throws Exception {

        Vaccination vaccination = new Vaccination("АКДС", "Y", new Date("15-MAR-20"), null);

        mvc.perform(get("/api/vaccinations/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.vaccine").value(vaccination.getVaccine()));
    }


    @Test
    public void whenCreateVaccination_thenExistsVaccination() throws Exception {

        Vaccination vaccination = new Vaccination("АКДС", "Y", new Date("15-MAR-20"), null);

        mvc.perform(post("/vaccinations")
                .content(asJsonString(vaccination))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[*]").exists());
    }

    @Test
    public void whenUpdateVaccination_thenEqualsData() throws Exception {

        Vaccination vaccination = new Vaccination("АКДС", "Y", new Date("15-MAR-21"), null);

        mvc.perform(put("/vaccinations/{id}", 26)
                .content(asJsonString(vaccination))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vaccine").value(vaccination.getVaccine()))
                .andExpect(jsonPath("$.permission").value(vaccination.getPermission()));
    }

    @Test
    public void whenDeleteVaccination_thenReturnStatusAccepted() throws Exception {
        mvc.perform(delete("/api/vaccinations/{id}", 35))
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
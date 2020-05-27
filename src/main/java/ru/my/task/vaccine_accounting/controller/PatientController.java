package ru.my.task.vaccine_accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.my.task.vaccine_accounting.model.Patient;
import ru.my.task.vaccine_accounting.model.Vaccination;
import ru.my.task.vaccine_accounting.service.PatientService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PatientController {

    @PersistenceContext
    public EntityManager entityManager;

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/patients")
    public ResponseEntity<?> create(@Valid @RequestBody Patient patient) {
        patientService.create(patient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/patients")
    public ResponseEntity<List<Patient>> read() {
        final List<Patient> patients = patientService.readAll();

        return patients != null && !patients.isEmpty()
                ? new ResponseEntity<>(patients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/patients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Patient patient) {
        final boolean updated = patientService.update(patient, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/patients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = patientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "patients/{patientId}/{section}")
    public ResponseEntity<Patient> readVaccinationsByPatientIdAndVaccinationId(@PathVariable(name = "patientId") int patientId,
                                                                               @PathVariable(name = "section") String section,
                                                                               @RequestParam(value = "page", defaultValue = "1") int page,
                                                                               @RequestParam(value = "size", defaultValue = "1") int size) {

        Patient patient;
        List<Vaccination> vaccinations;

        if (section.equals("vaccinations")) {
            patient = patientService.read(patientId);
            Query query = entityManager.createQuery("Select v From Vaccination v Join v.patients vp Where vp.id = :patientId").setParameter("patientId", patientId);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            vaccinations = query.getResultList();
            patient.setVaccinations(vaccinations);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return patient != null
                ? new ResponseEntity<>(patient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
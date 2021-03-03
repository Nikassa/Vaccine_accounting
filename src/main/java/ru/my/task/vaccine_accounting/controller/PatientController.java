package ru.my.task.vaccine_accounting.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.my.task.vaccine_accounting.controller.util.SwaggerDocuments;
import ru.my.task.vaccine_accounting.model.Patient;
import ru.my.task.vaccine_accounting.model.Vaccination;
import ru.my.task.vaccine_accounting.service.PatientService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(tags = "Операции c пациентами")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @PersistenceContext
    public EntityManager entityManager;

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation("Добавление пациента")
    @PostMapping(value = "/patients")
    public ResponseEntity<?> create(@Valid @RequestBody Patient patient) {
        logger.debug("Request to create patient: {} ", patient.toStringWithoutId());
        Patient createdPatient = patientService.create(patient);
        logger.debug("Added patient: {} ", createdPatient.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Получение списка пациентов", notes = SwaggerDocuments.GET_PATIENT_NOTES)
    @GetMapping(value = "/patients", produces = {"application/json"})
    public ResponseEntity<List<Patient>> read() {
        logger.debug("Request to get patients list.");
        final List<Patient> patients = patientService.readAll();

        return patients != null && !patients.isEmpty()
                ? new ResponseEntity<>(patients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Редактирование пациента")
    @PutMapping(value = "/patients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Patient patient) {
        logger.debug("Request to update patient: {} ", patient.toString());
        final boolean updated = patientService.update(patient, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation("Удаление пациента")
    @DeleteMapping(value = "/patients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        logger.debug("Request to delete patient by id: {}", id);
        final boolean deleted = patientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Получение пациента по идентификатору", notes = SwaggerDocuments.GET_PATIENT_NOTES)
    @GetMapping(value = "patients/{patientId}/{section}", produces = {"application/json"})
    public ResponseEntity<Patient> readPatientAndVaccinationsByPatientId(@PathVariable(name = "patientId") int patientId,
                                                                         @PathVariable(name = "section") String section,
                                                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                                                         @RequestParam(value = "size", defaultValue = "1") int size) {

        Patient patient;
        List<Vaccination> vaccinations;
        logger.debug("Request to get patient with vaccinations by id: {}, page {}, size {}", patientId, page, size);

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
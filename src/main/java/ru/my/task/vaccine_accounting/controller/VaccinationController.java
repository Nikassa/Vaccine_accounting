package ru.my.task.vaccine_accounting.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.my.task.vaccine_accounting.controller.util.SwaggerDocuments;
import ru.my.task.vaccine_accounting.model.Vaccination;
import ru.my.task.vaccine_accounting.service.VaccinationService;

@RestController
@RequestMapping(value = "/api")
@Api(tags = "Операции c прививками")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @ApiOperation("Добавление прививки")
    @PostMapping(value = "/vaccinations")
    public ResponseEntity<?> create(@RequestBody Vaccination vaccination) {
        vaccinationService.create(vaccination);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Получение прививки по идентификатору", notes = SwaggerDocuments.GET_VACCINATION_NOTES)
    @GetMapping(value = "/vaccinations/{id}")
    public ResponseEntity<Vaccination> read(@PathVariable(name = "id") int id) {
        final Vaccination vaccination = vaccinationService.read(id);

        return vaccination != null
                ? new ResponseEntity<>(vaccination, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Редактирование прививки")
    @PutMapping(value = "/vaccinations/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Vaccination vaccination) {
        final boolean updated = vaccinationService.update(vaccination, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @ApiOperation("Удаление прививки")
    @DeleteMapping(value = "/vaccinations/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = vaccinationService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
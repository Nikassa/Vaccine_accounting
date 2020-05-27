package ru.my.task.vaccine_accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.my.task.vaccine_accounting.model.Vaccination;

public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {

}
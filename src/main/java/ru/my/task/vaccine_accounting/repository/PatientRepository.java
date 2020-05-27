package ru.my.task.vaccine_accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.my.task.vaccine_accounting.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
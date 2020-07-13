package ru.my.task.vaccine_accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.my.task.vaccine_accounting.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

//    @Query("SELECT p FROM Patient p WHERE p.id = :id")
//    public Patient findPatientById( @Param("id") Integer id);
//    public Patient findPatientById(int id);

//    @Query("UPDATE Patient p SET p.name = 'lastname' WHERE p.id = :id")
    @Query("UPDATE Patient p SET name = 'lastname' WHERE id = 1")
    public int updatePatientNameById(@Param("id") Integer id);
}
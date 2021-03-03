package ru.my.task.vaccine_accounting.service;

import org.springframework.stereotype.Service;
import ru.my.task.vaccine_accounting.model.Patient;
import ru.my.task.vaccine_accounting.model.PatientDTO;
import ru.my.task.vaccine_accounting.repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.my.task.vaccine_accounting.service.Helper.patientFromDTO;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient create(Patient patient) {
        patientRepository.saveAndFlush(patient);
        return patient;
    }

    @Override
    public List<Patient> readAll() {
        List<Patient> users = patientRepository.findAll();
        users = users.stream().map(user -> patientFromDTO(new PatientDTO(user))).collect(Collectors.toList());
        return users;
    }

    @Override
    public Patient read(int id) {
        Patient patient = patientRepository.getOne(id);
        patient = patientFromDTO(new PatientDTO(patient));
        return patient;
    }

    @Override
    public boolean update(Patient patient, int id) {
        if (patientRepository.existsById(id)) {
            patient.setId(id);
            patientRepository.saveAndFlush(patient);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
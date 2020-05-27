package ru.my.task.vaccine_accounting.service;

import ru.my.task.vaccine_accounting.model.Patient;
import ru.my.task.vaccine_accounting.model.PatientDTO;

public class Helper {

    public static Patient patientFromDTO(PatientDTO userDTO) {
        return new Patient(userDTO.getId(), userDTO.getLastName(), userDTO.getFirstName(), userDTO.getMiddleName(), userDTO.getBirthDate(),
                userDTO.getGender(), userDTO.getInsuranceNumber());
    }

}

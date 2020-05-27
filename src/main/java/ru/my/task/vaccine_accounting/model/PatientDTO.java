package ru.my.task.vaccine_accounting.model;

import java.util.Date;

public class PatientDTO {

    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private String gender;
    private String insuranceNumber;

    public PatientDTO() {
    }

    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.lastName = patient.getLastName();
        this.firstName = patient.getFirstName();
        this.middleName = patient.getMiddleName();
        this.birthDate = patient.getBirthDate();
        this.gender = patient.getGender();
        this.insuranceNumber = patient.getInsuranceNumber();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

}
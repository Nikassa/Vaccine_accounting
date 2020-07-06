package ru.my.task.vaccine_accounting.controller.util;

public class SwaggerDocuments {

    public static final String GET_PATIENT_NOTES = "Параметры запроса: \n" +
            "id - идентификатор" + "\n" +
            "lastName - фамилия" + "\n" +
            "firstName - имя" + "\n" +
            "middleName - отчество" + "\n" +
            "birthDate - дата рождения в формате dd.mm.yyyy" + "\n" +
            "gender - пол" + "\n" +
            "insuranceNumber - СНИЛС в формате ХХХ-ХХХ-ХХХ ХХ";

    public static final String GET_VACCINATION_NOTES = "Параметры запроса: \n" +
            "id - идентификатор" + "\n" +
            "vaccine - препарат" + "\n" +
            "permission - согласие на прививку" + "\n" +
            "vaccination_date - дата проведения в формате dd.mm.yyyy";
}

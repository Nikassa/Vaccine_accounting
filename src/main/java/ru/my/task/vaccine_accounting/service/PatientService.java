package ru.my.task.vaccine_accounting.service;

import ru.my.task.vaccine_accounting.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     * Создает нового пациента
     *
     * @param patient - пациент для создания
     */
    void create(Patient patient);

    /**
     * Возвращает список всех имеющихся пациентов
     *
     * @return список пациентов
     */
    List<Patient> readAll();

    /**
     * Возвращает пациента по его ID
     *
     * @param id - ID пациента
     * @return - объект пациента с заданным ID
     */
    Patient read(int id);

    /**
     * Обновляет пациента с заданным ID,
     * в соответствии с переданным пациентом
     *
     * @param patient - пациент в соответствии с которым нужно обновить данные
     * @param id      - id пациента которого нужно обновить
     * @return - true - если данные были обновлены, иначе - false
     */
    boolean update(Patient patient, int id);

    /**
     * Удаляет пациента с заданным ID
     *
     * @param id - id пациента, которого нужно удалить
     * @return - true - если пациент был удален, иначе - false
     */
    boolean delete(int id);

}
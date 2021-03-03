package ru.my.task.vaccine_accounting.service;

import ru.my.task.vaccine_accounting.model.Vaccination;

import java.util.List;

public interface VaccinationService {

    /**
     * Создание нового препарата
     *
     * @param vaccination - препарат для создания
     */
    Vaccination create(Vaccination vaccination);

    /**
     * Возвращает список всех имеющихся препаратов
     *
     * @return список препаратов
     */
    List<Vaccination> readAll();

    /**
     * Возвращает препарат по его ID
     *
     * @param id - ID препарата
     * @return - объект препарата с заданным ID
     */
    Vaccination read(int id);

    /**
     * Обновляет препарат с заданным ID,
     * в соответствии с переданным препаратом
     *
     * @param vaccination - препарат в соответсвии с которым нужно обновить данные
     * @param id          - id препарата которого нужно обновить
     * @return - true - если данные были обновлены, иначе - false
     */
    boolean update(Vaccination vaccination, int id);

    /**
     * Удаляет препарат с заданным ID
     *
     * @param id - id препарата, который нужно удалить
     * @return - true - если препарат был удален, иначе - false
     */
    boolean delete(int id);

}
package ru.my.task.vaccine_accounting.service;

import org.springframework.stereotype.Service;
import ru.my.task.vaccine_accounting.model.Vaccination;
import ru.my.task.vaccine_accounting.repository.VaccinationRepository;

import java.util.List;

@Service
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    public VaccinationServiceImpl(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    @Override
    public void create(Vaccination vaccination) {
        vaccinationRepository.save(vaccination);
    }

    @Override
    public List<Vaccination> readAll() {
        return vaccinationRepository.findAll();
    }

    @Override
    public Vaccination read(int id) {
        return vaccinationRepository.getOne(id);
    }

    @Override
    public boolean update(Vaccination vaccination, int id) {
        if (vaccinationRepository.existsById(id)) {
            vaccination.setId(id);
            vaccinationRepository.save(vaccination);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (vaccinationRepository.existsById(id)) {
            vaccinationRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
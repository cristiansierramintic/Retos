package com.usa.api.service;

import com.usa.api.model.Specialty;
import com.usa.api.repository.SpecialtyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }

    public Optional<Specialty> getSpecialty(int id) {
        return specialtyRepository.getSpecialty(id);
    }

    public Specialty save(Specialty specialty) {
        if (specialty.getId() == null) {
            return specialtyRepository.save(specialty);
        } else {
            Optional<Specialty> specialtyAux = specialtyRepository.getSpecialty(specialty.getId());
            if (specialtyAux.isEmpty()) {
                return specialtyRepository.save(specialty);
            } else {
                return specialty;
            }
        }
    }

    public Specialty update(Specialty specialty) {
        if (specialty.getId() != null) {
            Optional<Specialty> specialtyAux = specialtyRepository.getSpecialty(specialty.getId());
            if (!specialtyAux.isEmpty()) {
                if (specialty.getName() != null) {
                    specialtyAux.get().setName(specialty.getName());
                }
                if (specialty.getDescription() != null) {
                    specialtyAux.get().setDescription(specialty.getDescription());
                }
                return specialtyRepository.save(specialtyAux.get());
            }
        }
        return specialty;
    }

    public boolean delete(int id) {
        Optional<Specialty> specialtyAux = specialtyRepository.getSpecialty(id);
        if (!specialtyAux.isEmpty()) {
            specialtyRepository.delete(specialtyAux.get());
            return true;
        }
        return false;
    }
}

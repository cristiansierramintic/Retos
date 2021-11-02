package com.usa.api.repository;

import com.usa.api.model.Doctor;
import com.usa.api.repository.crud.DoctorCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository {

    @Autowired
    private DoctorCrudRepository doctorCrudRepository;

    public List<Doctor> getAll() {
        return (List<Doctor>) doctorCrudRepository.findAll();
    }

    public Optional<Doctor> getDoctor(int id) {
        return doctorCrudRepository.findById(id);
    }

    public Doctor save(Doctor doctor) {
        return doctorCrudRepository.save(doctor);
    }

    public void delete(Doctor doctor) {
        doctorCrudRepository.delete(doctor);
    }
}

package com.usa.api.service;

import com.usa.api.model.Doctor;
import com.usa.api.repository.DoctorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }

    public Optional<Doctor> getDoctor(int id) {
        return doctorRepository.getDoctor(id);
    }

    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null) {
            return doctorRepository.save(doctor);
        } else {
            Optional<Doctor> doctorAux = doctorRepository.getDoctor(doctor.getId());
            if (doctorAux.isEmpty()) {
                return doctorRepository.save(doctor);
            } else {
                return doctor;
            }
        }
    }

    public Doctor update(Doctor doctor) {
        if (doctor.getId() != null) {
            Optional<Doctor> doctorAux = doctorRepository.getDoctor(doctor.getId());
            if (!doctorAux.isEmpty()) {
                if (doctor.getDepartment()!= null) {
                    doctorAux.get().setDepartment(doctor.getDepartment());
                }
                if (doctor.getYear() != null) {
                    doctorAux.get().setYear(doctor.getYear());
                }
                if (doctor.getName() != null) {
                    doctorAux.get().setName(doctor.getName());
                }
                if (doctor.getDescription() != null) {
                    doctorAux.get().setDescription(doctor.getDescription());
                }
                return doctorRepository.save(doctorAux.get());
            }
        }
        return doctor;
    }

    public boolean delete(int id) {
        Optional<Doctor> doctorAux = doctorRepository.getDoctor(id);
        if (!doctorAux.isEmpty()) {
            doctorRepository.delete(doctorAux.get());
            return true;
        }
        return false;
    }
}

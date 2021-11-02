package com.usa.api.repository.crud;

import com.usa.api.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorCrudRepository extends CrudRepository<Doctor, Integer>{
    
}

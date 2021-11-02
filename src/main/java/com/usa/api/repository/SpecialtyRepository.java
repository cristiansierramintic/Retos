
package com.usa.api.repository;

import com.usa.api.model.Specialty;
import com.usa.api.repository.crud.SpecialtyCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialtyRepository {
    
    @Autowired
    private SpecialtyCrudRepository specialtyCrudRepository;
    
    public List<Specialty> getAll() {
        return (List<Specialty>) specialtyCrudRepository.findAll();
    }
    
    public Optional<Specialty> getSpecialty(int id){
        return specialtyCrudRepository.findById(id);
    }
    
    public Specialty save(Specialty specialty) {
        return specialtyCrudRepository.save(specialty);
    }
    
    public void delete(Specialty specialty) {
        specialtyCrudRepository.delete(specialty);
    }
}

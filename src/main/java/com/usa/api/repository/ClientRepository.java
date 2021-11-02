/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.api.repository;

import com.usa.api.model.Client;
import com.usa.api.repository.crud.ClientCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
    
    @Autowired
    private ClientCrudRepository clientCrudRepository;
    
    public List<Client> getAll() {
        return (List<Client>) clientCrudRepository.findAll();
    }
    
    public Optional<Client> getClient(int id) {
        return clientCrudRepository.findById(id);
    }
    
    public Client save(Client client) {
        return clientCrudRepository.save(client);
    }
    
    public void delete(Client client) {
        clientCrudRepository.delete(client);
    }
}

package com.usa.api.service;

import com.usa.api.model.Client;
import com.usa.api.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> clientAux = clientRepository.getClient(client.getIdClient());
            if (clientAux.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> clientAux = clientRepository.getClient(client.getIdClient());
            if (!clientAux.isEmpty()) {
                if (client.getName() != null) {
                    clientAux.get().setName(client.getName());
                }
                if (client.getEmail() != null) {
                    clientAux.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    clientAux.get().setPassword(client.getPassword());
                }
                if (client.getAge() != null) {
                    clientAux.get().setAge(client.getAge());
                }
                return clientRepository.save(clientAux.get());
            }
        }
        return client;
    }

    public boolean delete(int id) {
        Optional<Client> clientAux = clientRepository.getClient(id);
        if (!clientAux.isEmpty()) {
            clientRepository.delete(clientAux.get());
            return true;
        }
        return false;
    }
}

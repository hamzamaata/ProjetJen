package net.meryem.client.service;

import net.meryem.client.entities.Client;
import net.meryem.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
         return clientRepository.findAll();
         }

    public Client findById(Long id) throws Exception {
         return clientRepository.findById(id).orElseThrow(() -> new
                Exception("Invalid Client ID"));
         }
    public void addClient(Client client) {
         clientRepository.save(client);
         }

}

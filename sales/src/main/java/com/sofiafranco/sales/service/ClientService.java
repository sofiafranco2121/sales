
package com.sofiafranco.sales.service;

import com.sofiafranco.sales.model.Client;
import com.sofiafranco.sales.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sofiafranco.sales.repository.IClientRepository;
import com.sofiafranco.sales.repository.IProductRepository;

@Service
public class ClientService implements IClientService {

 @Autowired
    private IClientRepository repoClient;

    @Override
    public List<Client> getClients() {
        
    List<Client> clientList = repoClient.findAll();
    return clientList;
    }

    @Override
    public void saveClient(Client client) {
        repoClient.save(client);
    }

    @Override
    public void deleteClient(Long id_client){
        repoClient.deleteById(id_client);
    }

    @Override
    public Client findClient(Long id_client) {
        return repoClient.findById(id_client).orElse(null);
    }

    @Override
    public void editClient(Long idOriginal, Long idNewClient, String newFname, 
            String newLname, String newDNI) {
        
        
            Client client = this.findClient(idOriginal);
            
            
            client.setId_client(idNewClient);
            client.setFname(newFname);
            client.setLname(newLname);
            client.setDni(newDNI);
            
            
            
            this.saveClient(client);
    }
    
    
}

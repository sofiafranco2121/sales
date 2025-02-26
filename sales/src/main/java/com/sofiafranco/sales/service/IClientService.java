
package com.sofiafranco.sales.service;

import com.sofiafranco.sales.model.Client;
import com.sofiafranco.sales.model.Product;
import java.util.List;


public interface IClientService {
    
    public List<Client> getClients();

    
    public void saveClient(Client client);

    
    public void deleteClient(Long id_client);

    
    public Client findClient(Long id_client);

    
    public void editClient(Long idOriginal, Long idNewClient,
                            String fname,
                            String lname,
                            String dni
                           );
    
    
}

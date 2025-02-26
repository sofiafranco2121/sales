
package com.sofiafranco.sales.controller;

import com.sofiafranco.sales.model.Client;
import com.sofiafranco.sales.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sofiafranco.sales.service.IClientService;
import com.sofiafranco.sales.service.IProductService;

@RestController
public class ClientController {
    
    @Autowired
    private IClientService clientServ;
    
    @GetMapping ("/clients")
    public List<Client> getClients() {
        
        return clientServ.getClients();
    }
    
    @PostMapping ("/clients/create")
    public String saveClient (@RequestBody Client client) {
        clientServ.saveClient(client);
        
        return "The client was created successfully";
    }
    
    @DeleteMapping ("/clients/delete/{id_client}")
    public String deleteCliente(@PathVariable Long id_client) {
        clientServ.deleteClient(id_client);
        return "The client was deleted successfully";
    }
    
      @GetMapping ("/clients/{id_client}")
    public Client findCliente(@PathVariable Long id_client) {
        
        return clientServ.findClient(id_client);
    }
    
    @PutMapping ("/clients/edit/{id_client}")
    public Client editCliente (@PathVariable Long id_client,
            @RequestParam(required = false, name = "id_client") Long idNewClient,
            @RequestParam(required = false, name = "fname") String newFname,
            @RequestParam(required = false, name = "lname") String newLname,
            @RequestParam(required = false, name = "dni") String newDNI
            
    ) {
            
        clientServ.editClient(id_client, idNewClient, newFname, newLname, newDNI);
        
        Client client = clientServ.findClient(idNewClient);
        
        return client;

    }
    
    
}

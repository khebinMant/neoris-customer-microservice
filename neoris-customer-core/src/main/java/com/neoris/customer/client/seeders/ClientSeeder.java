package com.neoris.customer.person.controllers.seeders;

import com.neoris.customer.client.services.IClientService;
import com.neoris.customer.client.vo.CreateClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientSeeder {

    @Autowired
    IClientService clientService;

    public void fillStartClients (){
        clientService.create(CreateClientVo.builder()
            .build());
    }
}

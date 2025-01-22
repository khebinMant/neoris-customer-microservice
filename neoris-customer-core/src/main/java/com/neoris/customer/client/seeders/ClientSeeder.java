package com.neoris.customer.client.seeders;

import com.neoris.customer.client.services.IClientService;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.person.services.IPersonService;
import com.neoris.customer.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientSeeder {

    @Autowired
    IClientService clientService;
    @Autowired
    IPersonService personService;

    public void fillStartClients (){

        clientService.create(CreateClientVo.builder()
            .password("1234")
            .identityNumber(1235678910L)
            .status("1")
            .createdByUser(1001L)
            .lastModifiedDate(DateUtil.currentDate())
            .createdFromIp("192.168.0.1")
            .updatedFromIp("192.168.0.1")
            .build());

        clientService.create(CreateClientVo.builder()
            .password("5678")
            .identityNumber(1235678911L)
            .status("1")
            .createdByUser(1001L)
            .lastModifiedDate(DateUtil.currentDate())
            .createdFromIp("192.168.0.1")
            .updatedFromIp("192.168.0.1")
            .build());

        clientService.create(CreateClientVo.builder()
            .password("1245")
            .identityNumber(1235678912L)
            .status("1")
            .createdByUser(1001L)
            .lastModifiedDate(DateUtil.currentDate())
            .createdFromIp("192.168.0.1")
            .updatedFromIp("192.168.0.1")
            .build());
    }
}

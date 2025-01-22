package com.neoris.customer.person.seeders;


import com.neoris.customer.person.services.IPersonService;
import com.neoris.customer.person.vo.CreatePersonVo;
import com.neoris.customer.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonSeeder {
    @Autowired
    IPersonService personService;

    public void fillStartPersons (){
        personService.create(CreatePersonVo.builder()
            .name("Jose")
            .surname("Lema")
            .gender("M")
            .birthDate(DateUtil.currentDate())
            .identityNumber(1235678910L)
            .address("Otavalo sn y principal")
            .phone("0985871145")
            .status("1")
            .createdByUser(1001L)
            .lastModifiedDate(DateUtil.currentDate())
            .createdFromIp("192.168.0.1")
            .updatedFromIp("192.168.0.1")
            .build());

        personService.create(CreatePersonVo.builder()
                .name("Marianela")
                .surname("Montalvo")
                .gender("F")
                .birthDate(DateUtil.currentDate())
                .identityNumber(1235678911L)
                .address("Amazonas y NNUU")
                .phone("0984147741")
                .status("1")
                .createdByUser(1001L)
                .lastModifiedDate(DateUtil.currentDate())
                .createdFromIp("192.168.0.1")
                .updatedFromIp("192.168.0.1")
                .build());

        personService.create(CreatePersonVo.builder()
                .name("Juan")
                .surname("Osorio")
                .gender("M")
                .birthDate(DateUtil.currentDate())
                .identityNumber(1235678912L)
                .address("13 junio y Equinoccial")
                .phone("0963251147")
                .status("1")
                .createdByUser(1001L)
                .lastModifiedDate(DateUtil.currentDate())
                .createdFromIp("192.168.0.1")
                .updatedFromIp("192.168.0.1")
                .build());
    }
}

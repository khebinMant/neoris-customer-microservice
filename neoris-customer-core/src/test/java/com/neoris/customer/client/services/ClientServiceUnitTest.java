package com.neoris.customer.client.services;

import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.repositories.ClientRepository;
import com.neoris.customer.client.services.ClientService;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.person.entities.PersonEntity;
import com.neoris.customer.person.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
* Test de integración
* */
public class ClientServiceUnitTest {
    @Mock
    private PersonService personService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnClientEntity_WhenValidDataProvided() throws Exception {

        Long identityNumber = 123456789L;
        CreateClientVo createClientVo = new CreateClientVo();
        createClientVo.setIdentityNumber(identityNumber);
        createClientVo.setPassword("password123");

        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonId(1L);
        personEntity.setIdentityNumber(identityNumber);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientId(1L);
        clientEntity.setPassword("password123");
        clientEntity.setPerson(personEntity);

        when(personService.findByIdentificationNumber(identityNumber)).thenReturn(personEntity);
        when(clientRepository.create(createClientVo, personEntity)).thenReturn(clientEntity);

        // Act
        ClientEntity result = clientService.create(createClientVo);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(clientEntity.getClientId(), result.getClientId(), "The clientId should match");
        assertEquals(clientEntity.getPassword(), result.getPassword(), "The password should match");
        assertEquals(clientEntity.getPerson(), result.getPerson(), "The associated person should match");

        // Verify
        verify(personService, times(1)).findByIdentificationNumber(identityNumber);
        verify(clientRepository, times(1)).create(createClientVo, personEntity);
    }
}

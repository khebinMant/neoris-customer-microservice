package com.neoris.customer.client;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.customer.client.controllers.ClientController;
import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.services.ClientService;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.person.entities.PersonEntity;
import com.neoris.customer.person.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateClientVo createClient;
    private PersonEntity mockPerson;
    private ClientEntity mockClient;

    @BeforeEach
    public void setUp() {
        // Configuración de datos de prueba
        createClient = CreateClientVo.builder()
                .identityNumber(123456789L)
                .password("securePassword")
                .status("active")
                .createdByUser(1L)
                .createdFromIp("192.168.0.1")
                .updatedFromIp("192.168.0.1")
                .build();

        mockPerson = new PersonEntity();
        mockPerson.setPersonId(1L);
        mockPerson.setName("Juan");
        mockPerson.setSurname("Pérez");
        mockPerson.setIdentityNumber(createClient.getIdentityNumber());

        mockClient = new ClientEntity();
        mockClient.setClientId(1L);
        mockClient.setPassword(createClient.getPassword());
        mockClient.setPerson(mockPerson);  // Establecemos la relación con PersonEntity
    }

    @Test
    public void testCreateClient() throws Exception {
        // Simular el comportamiento de los servicios
        when(personService.findByIdentificationNumber(createClient.getIdentityNumber())).thenReturn(mockPerson);
        when(clientService.create(createClient)).thenReturn(mockClient);

        // Realizar la prueba del endpoint
        mockMvc.perform(post("/clientApi/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createClient)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Objeto fue creado"))
                .andExpect(jsonPath("$.data.password").value("securePassword"))
                .andExpect(jsonPath("$.data.person.name").value("Juan"))
                .andExpect(jsonPath("$.data.person.surname").value("Pérez"))
                .andExpect(jsonPath("$.data.status").value("active"));
    }
}

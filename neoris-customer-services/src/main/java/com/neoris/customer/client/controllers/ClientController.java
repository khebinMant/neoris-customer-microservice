package com.neoris.customer.client.controllers;


import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.services.IClientService;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.client.vo.UpdateClientVo;
import com.neoris.customer.common.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Client
 *
 * @author Kevin
 * @version 1.0
 */
@RestController
@RequestMapping("clientes")
@Lazy
@Tag(name = "Clientes", description = "Clientes API")
public class ClientController {

    @Lazy
    @Autowired
    private IClientService clientService;

    /**
     * Add a new Client
     *
     * @param createClient Client to create
     * @return Status code
     */
    @PostMapping
    @Operation(summary = "Create new Client object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client Object has been created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Client Object already exists")
    })
    public ResponseEntity<Response<ClientEntity>> create(@RequestBody CreateClientVo createClient) {
        return new ResponseEntity<>(Response.<ClientEntity>builder()
                .data(this.clientService.create(createClient))
                .message("Objeto fue creado").build(), HttpStatus.CREATED);
    }

    /**
     * Find client value by ID
     *
     * @param clientId String
     * @return Catalogue value entity
     */
    @GetMapping("/{clientId}")
    @Operation(summary = "Find client by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find client by Id"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Response<ClientEntity>> findClientById(@NotBlank @PathVariable Long clientId) {
        return new ResponseEntity<>(Response.<ClientEntity>builder()
                .data(this.clientService.findById(clientId)).build(), HttpStatus.OK);
    }

    /**
     * Update Client.
     * Se comentA este método porque es mejor obtener el cliente dada su cedula ya
     * que también es única pero lo mantengo para constancia del CRUD
     *
     * @param clientId Long ID
     * @param updateClientVo UpdateClient
     * @return Status code
     */
    @PatchMapping("/{clientId}")
    @Operation(summary = "Update Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
    })
    public ResponseEntity<Response<Void>> update(@NotBlank @PathVariable Long clientId, @RequestBody UpdateClientVo updateClientVo) {
        this.clientService.updateClient(updateClientVo, clientId);
        return new ResponseEntity<>(Response.<Void>builder().message("Actualizado con éxito").build(), HttpStatus.OK);
    }

    /**
     * Find person value by identityNumber
     *
     * @param identityNumber String
     * @return PersonEntity
     */
    @GetMapping("identityNumber/{identityNumber}")
    @Operation(summary = "Find client by identityNumber")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find client by identityNumber"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Response<ClientEntity>> findClientByIdentityNumber(@NotBlank @PathVariable Long identityNumber) {
        return new ResponseEntity<>(Response.<ClientEntity>builder()
                .data(this.clientService.findByIdentificationPersonNumber(identityNumber)).build(), HttpStatus.OK);
    }

    /**
     * Find all Clients Objects.
     *
     * @return Status code
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all Accounts")
    @ApiResponses(value = { @ApiResponse(responseCode =  "200", description = "List of flow objects")})
    public ResponseEntity<Response<List<ClientEntity>>> findAll(){
        return new ResponseEntity<>(Response.<List<ClientEntity>>builder()
                .data(clientService.findAllClients())
                .message("SUCCESS")
                .build(),
                HttpStatus.OK);
    }

}

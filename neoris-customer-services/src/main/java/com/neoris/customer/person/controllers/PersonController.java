package com.neoris.customer.person.controllers;

import com.neoris.customer.common.web.Response;
import com.neoris.customer.person.entities.PersonEntity;
import com.neoris.customer.person.services.IPersonService;
import com.neoris.customer.person.vo.CreatePersonVo;
import com.neoris.customer.person.vo.UpdatePersonVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Person
 *
 * @author Kevin
 * @version 1.0
 */
@RestController
@RequestMapping("personas")
@Lazy
@Tag(name = "Personas", description = "Personas API")
public class PersonController {
    @Lazy
    @Autowired
    private IPersonService personService;

    /**
     * Add a new Person
     *
     * @param createPerson Person to create
     * @return Status code
     */
    @PostMapping
    @Operation(summary = "Create new Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person Object has been created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Person Object already exists")
    })
    public ResponseEntity<Response<PersonEntity>> create(@RequestBody CreatePersonVo createPerson) {
        return new ResponseEntity<>(Response.<PersonEntity>builder()
                .data(this.personService.create(createPerson))
                .message("Objeto fue creado").build(), HttpStatus.CREATED);
    }

    /**
     * Find person value by ID
     *
     * @param personId String
     * @return PersonEntity
     */
    @GetMapping("{personId}")
    @Operation(summary = "Find person by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find person by Id"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Response<PersonEntity>> findValueById(@NotBlank @PathVariable Long personId) {
        return new ResponseEntity<>(Response.<PersonEntity>builder()
                .data(this.personService.findById(personId)).build(), HttpStatus.OK);
    }

    /**
     * Find person value by identityNumber
     *
     * @param identityNumber String
     * @return PersonEntity
     */
    @GetMapping("identityNumber/{identityNumber}")
    @Operation(summary = "Find person by identityNumber")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find person by identityNumber"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Response<PersonEntity>> findPersonByIdentityNumber(@NotBlank @PathVariable Long identityNumber) {
        return new ResponseEntity<>(Response.<PersonEntity>builder()
                .data(this.personService.findByIdentificationNumber(identityNumber)).build(), HttpStatus.OK);
    }

    /**
     * Update Person.
     *
     * @param personId Long ID
     * @param updatePersonVo UpdatePerson
     * @return Status code
     */
    @PatchMapping("/{personId}")
    @Operation(summary = "Update Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
    })
    public ResponseEntity<Response<Void>> update(@NotBlank @PathVariable Long personId, @RequestBody UpdatePersonVo updatePersonVo) {
        this.personService.updatePerson(updatePersonVo, personId);
        return new ResponseEntity<>(Response.<Void>builder().message("Actualizado con éxito").build(), HttpStatus.OK);
    }

}

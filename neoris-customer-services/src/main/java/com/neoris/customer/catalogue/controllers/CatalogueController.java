package com.neoris.customer.catalogue.controllers;

import com.neoris.customer.catalogue.entities.CatalogueTypeEntity;
import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.services.ICatalogueTypeService;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueValue.CatalogueValueVo;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Catalogues resources
 *
 * @author Kevin
 * @version 1.0
 */
@RestController
@RequestMapping("catalogues")
@Lazy
@Tag(name = "Catalogues", description = "Catalogues API")
public class CatalogueController {

    @Lazy
    @Autowired
    private ICatalogueTypeService catalogueService;

    /**
     * Find all Catalogue types
     *
     * @param query Query
     * @return List of Catalogues
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all catalogues")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved Catalogue Types")
    })
    public ResponseEntity<Response<List<CatalogueTypeEntity>>> findAllTypes(
            @Valid QueryCatalogueType query) {
        return new ResponseEntity<>(Response.<List<CatalogueTypeEntity>>builder().data(catalogueService.findAllTypes(query)).build(),
                HttpStatus.OK);
    }

    /**
     * Find catalogue type
     *
     * @param catalogueTypeId Long Id of catalogue
     * @return Catalogue Type
     */
    @GetMapping("{catalogueTypeId}")
    @Operation(summary = "Find catalogue by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved Catalogue Type by Id"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Response<CatalogueTypeEntity>> findTypeById(@NotBlank @PathVariable Long catalogueTypeId) {
        return new ResponseEntity<>(Response.<CatalogueTypeEntity>builder().data(this.catalogueService.findTypeById(catalogueTypeId)).build(), HttpStatus.OK);
    }

    /**
     * Find all values of type catalogue
     *
     * @param catalogueTypeId Id of catalogue
     * @return List of catalogue values
     */
    @GetMapping("{catalogueTypeId}/values")
    @Operation(summary = "Find values of catalogue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find all catalogue values") })
    public ResponseEntity<Response<List<CatalogueValueEntity>>> findAllValues(@NotBlank @PathVariable Long catalogueTypeId) {
        return new ResponseEntity<>(Response.<List<CatalogueValueEntity>>builder().data(this.catalogueService.findAllValues(catalogueTypeId)).build(), HttpStatus.OK);
    }

    /**
     * Find all values of type catalogue
     *
     * @param typeCode code of catalogueType
     * @return List of catalogue values
     */
    @GetMapping("type/{typeCode}")
    @Operation(summary = "Find values of catalogue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find all catalogue values by code type") })
    public ResponseEntity<Response<List<CatalogueValueVo>>> findAll(@NotBlank @PathVariable String typeCode) {
        return new ResponseEntity<>(Response.<List<CatalogueValueVo>>builder().data(this.catalogueService.findAllByCode(typeCode)).build(), HttpStatus.OK);
    }

    /**
     * Find catalogue value by Id
     *
     * @param catalogueValueId Id of catalogue value
     * @return Catalogue value entity
     */
    @GetMapping("values/{catalogueValueId}")
    @Operation(summary = "Find catalogue value by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find catalogue value by Id"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Response<CatalogueValueEntity>> findValueById(@NotBlank @PathVariable Long catalogueValueId) {
        return new ResponseEntity<>(Response.<CatalogueValueEntity>builder()
                .data(this.catalogueService.findValueById(catalogueValueId)).build(), HttpStatus.OK);
    }
}

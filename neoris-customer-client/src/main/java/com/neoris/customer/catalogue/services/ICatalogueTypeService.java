package com.neoris.customer.catalogue.services;

import com.neoris.customer.catalogue.entities.CatalogueTypeEntity;
import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.CreateCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueType.UpdateCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueValue.CatalogueValueVo;
import com.neoris.customer.catalogue.vo.CatalogueValue.CreateCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.UpdateCatalogueValue;
import com.neoris.customer.common.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Catalogue Service
 *
 * @author Kevin
 * @version 1.0
 */
public interface ICatalogueTypeService {

    /**
     * Find all Catalogue types
     *
     * @param query Query to find
     * @return List of catalogue types
     */
    List<CatalogueTypeEntity> findAllTypes(QueryCatalogueType query);

    /**
     * Find a catalogue by type Id
     *
     * @param catalogueTypeId Catalogue Type Id
     * @return Catalogue Type
     */
    CatalogueTypeEntity findTypeById(Long catalogueTypeId);

    /**
     * Create a new Catalogue type
     *
     * @param createCatalogueType Catalogue to create
     * @return New Catalogue
     */
    CatalogueTypeEntity createType(CreateCatalogueType createCatalogueType);

    /**
     * Update of type of catalogue
     *
     * @param catalogueTypeId Id of catalogue type
     * @param updateCatalogueType Update properties of catalogue
     */
    void updateType(Long catalogueTypeId, UpdateCatalogueType updateCatalogueType);

    /**
     * Inactive a type of catalogue
     *
     * @param catalogueTypeId Id of catalogue
     */
    void inactiveType(Long catalogueTypeId);

    /**
     * Find all values of catalogue
     *
     * @param catalogueTypeId Catalogue type Id
     * @return List of the catalogue values
     */
    List<CatalogueValueEntity> findAllValues(Long catalogueTypeId);

    /**
     * Add a new value to catalogue
     *
     * @param catalogueTypeId Id of type catalogue
     * @param createCatalogueValue Dto with data the new value
     * @return New catalogue type
     * @throws EntityNotFoundException If not present the catalogue type
     */
    CatalogueValueEntity addValue(Long catalogueTypeId, CreateCatalogueValue createCatalogueValue) throws EntityNotFoundException;

    /**
     * Find value by Id
     *
     * @param catalogueValueId Id of Value
     * @return Catalogue value
     * @throws EntityNotFoundException If not exist catalogue value
     */
    CatalogueValueEntity findValueById(Long catalogueValueId) throws EntityNotFoundException;

    /**
     * Find value by code
     *
     * @param catalogueValueCode Code of Value
     * @return Catalogue value
     * @throws EntityNotFoundException If not exist catalogue value
     */
    CatalogueValueEntity findValueByCode(String catalogueValueCode) throws EntityNotFoundException;

    /**
     * Update catalogue value
     *
     * @param catalogueTypeId Id of catalogue type
     * @param updateCatalogueValue Dto for update catalogue value
     * @throws EntityNotFoundException If not exist catalogue value
     */
    void updateValue(Long catalogueTypeId, UpdateCatalogueValue updateCatalogueValue) throws EntityNotFoundException;

    /**
     * Inactive catalogue value
     *
     * @param catalogueValueId Id fo catalogue value
     */
    void inactiveValue(Long catalogueValueId);

    /**
     * find all catalogues
     *
     * @param code of catalogue type
     * @return list of CatalogueValueVo
     */
    List<CatalogueValueVo> findAllByCode(String code);
}

package com.neoris.customer.catalogue.repositories;

import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.CatalogueValueVo;
import com.neoris.customer.catalogue.vo.CatalogueValue.CreateCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.UpdateCatalogueValue;
import com.neoris.customer.common.repositories.IQueryDslBaseRepository;

import java.util.List;

/**
 * Catalogue repository
 *
 * @author Kevin
 * @version 1.0
 */
public interface ICatalogueValueRepository extends IQueryDslBaseRepository<CatalogueValueEntity> {
    
    /**
     * Create new Catalogue Value
     *
     * @param catalogueTypeId Id Catalogue type
     * @param createCatalogueValue CreateCatalogueType
     * @return New catalogue value
     */
    CatalogueValueEntity create(Long catalogueTypeId, CreateCatalogueValue createCatalogueValue);

    /**
     * Find Catalogue value by Id
     *
     * @param catalogueValueId Long
     * @return CatalogueValue
     */
    CatalogueValueEntity findById(Long catalogueValueId);


    /**
     * Find Catalogue value by code
     *
     * @param code String
     * @return CatalogueValue
     */
    CatalogueValueEntity findByCode(String code);

    /** 
     * Update Catalogue Value
     *
     * @param catalogueValueId Catalogue value Id
     * @param updateCatalogueValue Catalogue value to enable
     */
    void updateEntity(Long catalogueValueId, UpdateCatalogueValue updateCatalogueValue);

    /**
     * Inactive Catalogue Value
     *
     * @param catalogueValueId Long
     */
    void inactive(Long catalogueValueId);

    /**
     * Find all Catalogues values
     * @return CatalogueValue List
     */
    List<CatalogueValueEntity> findAll(QueryCatalogueValue query);

    /**
     * Find all Catalogues values
     * @return CatalogueValueVo List
     */
    List<CatalogueValueVo> findAllByCode(String code);
}

package com.neoris.customer.catalogue.services;

import com.neoris.customer.catalogue.entities.CatalogueTypeEntity;
import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.repositories.ICatalogueTypeRepository;
import com.neoris.customer.catalogue.repositories.ICatalogueValueRepository;
import com.neoris.customer.catalogue.vo.CatalogueType.CreateCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueType.UpdateCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueValue.CatalogueValueVo;
import com.neoris.customer.catalogue.vo.CatalogueValue.CreateCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.UpdateCatalogueValue;
import com.neoris.customer.common.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Validated
@Lazy
@Service
@Transactional
public class CatalogueService implements ICatalogueTypeService {

    @Lazy
    @Autowired
    private ICatalogueTypeRepository catalogueTypeRepository;

    @Lazy
    @Autowired
    private ICatalogueValueRepository catalogueValueRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueTypeEntity createType(CreateCatalogueType createCatalogueType) {
        return catalogueTypeRepository.create(createCatalogueType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public CatalogueTypeEntity findTypeById(Long catalogueTypeId) {
        return Optional.ofNullable(catalogueTypeRepository.findById(catalogueTypeId))
            .orElseThrow(() -> new EntityNotFoundException("No existe el tipo de catalogo con id %s".formatted(catalogueTypeId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateType(Long catalogueTypeId, UpdateCatalogueType updateCatalogueType) {
        this.findTypeById(catalogueTypeId);
        catalogueTypeRepository.updateEntity(catalogueTypeId, updateCatalogueType);
    }

    @Override
    public void inactiveType(Long catalogueTypeId) {
        this.findTypeById(catalogueTypeId);
        catalogueTypeRepository.inactive(catalogueTypeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CatalogueTypeEntity> findAllTypes(QueryCatalogueType query) {
        return this.catalogueTypeRepository.findAll(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CatalogueValueEntity> findAllValues(Long catalogueTypeId) {
        return catalogueValueRepository.findAll(QueryCatalogueValue.builder()
                .catalogueTypeId(catalogueTypeId)
            .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueValueEntity addValue(Long catalogueTypeId, CreateCatalogueValue createCatalogueValue)
        throws EntityNotFoundException {
        CatalogueTypeEntity catalogueType = this.findTypeById(catalogueTypeId);

        return catalogueValueRepository.create(catalogueType.getCatalogueTypeId(), createCatalogueValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public CatalogueValueEntity findValueById(Long catalogueValueId)
        throws EntityNotFoundException {
        return Optional.ofNullable(catalogueValueRepository.findById(catalogueValueId))
            .orElseThrow(() -> new EntityNotFoundException("No existe el catalogo de valor con el id %s".formatted(catalogueValueId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueValueEntity findValueByCode(String catalogueValueCode)
        throws EntityNotFoundException {
            return Optional.ofNullable(catalogueValueRepository.findByCode(catalogueValueCode))
                .orElseThrow(() -> new EntityNotFoundException("No existe el catalogo de valor con el código %s".formatted(catalogueValueCode)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateValue(Long catalogueTypeId, UpdateCatalogueValue updateCatalogueValue)
        throws EntityNotFoundException {
        catalogueValueRepository.updateEntity(catalogueTypeId, updateCatalogueValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inactiveValue(Long catalogueValueId) {
        catalogueValueRepository.inactive(catalogueValueId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatalogueValueVo> findAllByCode(String code){
        return catalogueValueRepository.findAllByCode(code);
    }

}

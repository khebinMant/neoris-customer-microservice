package com.neoris.customer.catalogue.services;


import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.vo.CatalogueValue.UpdateCatalogueValue;

public interface ICatalogueValueService {

    CatalogueValueEntity findById(Long catalogueValueId);

    void update(Long catalogueValueId, UpdateCatalogueValue updateCatalogueValue);

    void delete(Long catalogueValueId);
}

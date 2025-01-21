package com.neoris.customer.catalogue.mappers;

import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.ResponseCatalogueValueVo;

public abstract class CatalogueValueMapper {
    public static ResponseCatalogueValueVo mapEntityToVo(CatalogueValueEntity catalogueValueEntity) {

        if (catalogueValueEntity == null) {
            return null;
        }

        ResponseCatalogueValueVo.ResponseCatalogueValueVoBuilder catalogueValueBuilder = ResponseCatalogueValueVo.builder();

        catalogueValueBuilder
                .catalogueValueId(catalogueValueEntity.getCatalogueValueId())
                .code(catalogueValueEntity.getCode())
                .name(catalogueValueEntity.getName())
                .description(catalogueValueEntity.getDescription())
                .status(catalogueValueEntity.getStatus())
                .catalogueTypeId(catalogueValueEntity.getCatalogueTypeId());

        return catalogueValueBuilder.build();

    }
}

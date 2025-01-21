package com.neoris.customer.catalogue.vo.CatalogueType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCatalogueValueVo {
    private Long catalogueValueId;
    private String code;
    private String name;
    private String description;
    private String status;

    private Long catalogueTypeId;

    private ResponseCatalogueTypeVo catalogueType;
}

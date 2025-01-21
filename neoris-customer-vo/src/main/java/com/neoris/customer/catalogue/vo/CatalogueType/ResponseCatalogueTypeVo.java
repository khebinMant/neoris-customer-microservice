package com.neoris.customer.catalogue.vo.CatalogueType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCatalogueTypeVo {
    private Long catalogueTypeId;
    private String code;
    private String name;
    private String description;
    private String status;
}

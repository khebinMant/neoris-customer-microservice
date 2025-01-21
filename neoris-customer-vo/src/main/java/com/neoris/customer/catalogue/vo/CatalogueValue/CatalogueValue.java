package com.neoris.customer.catalogue.vo.CatalogueValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogueValue {
    private Long catalogueValueId;
    private Long catalogueTypeId;
    private String code;
    private String name;
    private String description;
    private Long order;
    private String isDefault;
}

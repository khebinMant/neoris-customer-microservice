package com.neoris.customer.catalogue.vo.CatalogueType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QueryCatalogueType {
    private String code;
    private String name;
}

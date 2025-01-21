package com.neoris.customer.catalogue.vo.CatalogueValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogueValueVo {
    private Long catalogueValueId;
    private String code;
    private String name;
}

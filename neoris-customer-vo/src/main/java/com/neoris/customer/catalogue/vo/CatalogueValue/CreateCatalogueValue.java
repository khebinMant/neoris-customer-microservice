package com.neoris.customer.catalogue.vo.CatalogueValue;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatalogueValue {

    private Long catalogueTypeId;
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
    private Long order;
    private String isDefault;
}

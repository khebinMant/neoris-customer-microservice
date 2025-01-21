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
public class UpdateCatalogueValue {

    @NotBlank
    private String name;

    private String description;
    private Long placement;
    private String isDefault;
}

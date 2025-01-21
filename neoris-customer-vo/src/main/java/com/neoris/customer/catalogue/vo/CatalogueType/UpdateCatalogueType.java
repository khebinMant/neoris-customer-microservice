package com.neoris.customer.catalogue.vo.CatalogueType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCatalogueType {
    @NotBlank
    private String name;
    private String description;
}

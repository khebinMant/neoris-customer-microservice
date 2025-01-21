package com.neoris.customer.catalogue.entities;

import com.neoris.customer.common.entities.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity( name = "catalogue_value")
@Table( schema = "customer")
@EqualsAndHashCode(callSuper = true)
public class CatalogueValueEntity extends AbstractBaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogue_value_id", nullable = false, updatable = false)
    private Long catalogueValueId;

    @Column(name = "catalogue_type_id", nullable = false)
    private Long catalogueTypeId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Long placement;

    @Column(name = "is_default")
    private String isDefault;
}
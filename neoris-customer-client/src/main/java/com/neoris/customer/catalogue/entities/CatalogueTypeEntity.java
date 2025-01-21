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
@EqualsAndHashCode(callSuper = true)
@Entity( name = "catalogue_type")
@Table(schema = "customer")
public class CatalogueTypeEntity extends AbstractBaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogue_type_id", nullable = false, updatable = false)
    private Long catalogueTypeId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}

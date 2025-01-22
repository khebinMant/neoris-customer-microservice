package com.neoris.customer.management.catalogue.repositories;

import com.neoris.customer.catalogue.repositories.ICatalogueTypeRepository;
import com.neoris.customer.catalogue.entities.CatalogueTypeEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.CreateCatalogueType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CatalogueTypeRepositoryTest {
    @Autowired
    private ICatalogueTypeRepository catalogueTypeRepository;

    CatalogueTypeEntity catalogueType;

    @BeforeEach
    void setup(){
        catalogueType = CatalogueTypeEntity.builder()
                .code("TEST-CODE-ONE")
                .name("name test one")
                .build();
    }


    @DisplayName("Test to save new CatalogueType")
    @Test
    void testSaveCatalogueType(){
        CreateCatalogueType catalogueType = CreateCatalogueType.builder()
                .code("TEST-CODE-ONE")
                .name("name test one")
                .build();
        CatalogueTypeEntity newCatalogueType = catalogueTypeRepository.create(catalogueType);

        assertThat(newCatalogueType).isNotNull();
        assertThat(newCatalogueType.getCatalogueTypeId()).isGreaterThan(0);
    }

}
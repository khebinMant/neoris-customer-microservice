package com.neoris.customer.catalogue.controllers;

import com.neoris.customer.catalogue.entities.CatalogueTypeEntity;
import com.neoris.customer.catalogue.services.ICatalogueTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CatalogueControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICatalogueTypeService catalogueTypeService;

    @Test
    void testFindCatalogueTypeById() throws Exception {
        long catalogueTypeId = 1l;

        CatalogueTypeEntity catalogueType = CatalogueTypeEntity.builder()
                .code("CODE-ONE")
                .name("code one")
                .build();

        given(catalogueTypeService.findTypeById(catalogueTypeId)).willReturn(catalogueType);

        ResultActions response = mockMvc.perform(get("/catalogues/{catalogueTypeId}",catalogueTypeId));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code",is(catalogueType.getCode())))
                .andExpect(jsonPath("$.data.name",is(catalogueType.getName())));
    }
}

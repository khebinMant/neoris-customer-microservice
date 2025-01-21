package com.neoris.customer.catalogue.repositories;

import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.CatalogueValueVo;
import com.neoris.customer.catalogue.vo.CatalogueValue.CreateCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.UpdateCatalogueValue;
import com.neoris.customer.common.enums.Status;
import com.neoris.customer.common.repositories.JPAQueryDslBaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.neoris.customer.catalogue.entities.QCatalogueTypeEntity.catalogueTypeEntity;
import static com.neoris.customer.catalogue.entities.QCatalogueValueEntity.catalogueValueEntity;
import static com.querydsl.core.types.Projections.bean;

/**
 * {@inheritDoc}
 */
@Lazy
@Repository
public class CatalogueValueRepository extends JPAQueryDslBaseRepository<CatalogueValueEntity> implements ICatalogueValueRepository{

    public CatalogueValueRepository() {
        super(CatalogueValueEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueValueEntity create(Long catalogueTypeId, CreateCatalogueValue createCatalogueValue) {
        CatalogueValueEntity entity =  CatalogueValueEntity.builder()
            .catalogueTypeId(catalogueTypeId)
            .code(createCatalogueValue.getCode())
            .name(createCatalogueValue.getName())
            .description(createCatalogueValue.getDescription())
            .placement(createCatalogueValue.getOrder())
            .isDefault(createCatalogueValue.getIsDefault())
            .build();
        this.save(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueValueEntity findById(Long catalogueValueId) {
        return from(catalogueValueEntity)
            .select(bean(CatalogueValueEntity.class, catalogueValueEntity.catalogueValueId, catalogueValueEntity.code, catalogueValueEntity.name, catalogueValueEntity.description))
            .where(this.activeCatalogueCondition(catalogueValueId))
            .fetchFirst();
    }

    @Override
    public CatalogueValueEntity findByCode(String code) {
        return from(catalogueValueEntity)
            .where(catalogueValueEntity.code.eq(code))
            .fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntity(Long catalogueValueId, UpdateCatalogueValue updateCatalogueValue) {
        this.update(catalogueValueEntity)
            .where(this.activeCatalogueCondition(catalogueValueId))
            .set(catalogueValueEntity.name, updateCatalogueValue.getName())
            .set(catalogueValueEntity.description, updateCatalogueValue.getDescription())
            .set(catalogueValueEntity.placement, updateCatalogueValue.getPlacement())
            .set(catalogueValueEntity.isDefault, updateCatalogueValue.getIsDefault())
            .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inactive(Long catalogueValueId) {
        this.update(catalogueValueEntity)
            .where(this.activeCatalogueCondition(catalogueValueId))
            .set(catalogueValueEntity.status, Status.INACTIVE.value)
            .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatalogueValueEntity> findAll(QueryCatalogueValue query) {
        return from(catalogueValueEntity)
            .where(buildQuery(query))
            .orderBy(catalogueValueEntity.placement.asc())
            .fetch();
    }

    /**
     * Condition for active catalogue value
     *
     * @param catalogueValueId Id of catalogue
     * @return Condition
     */
    private BooleanBuilder activeCatalogueCondition(Long catalogueValueId) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(catalogueValueEntity.catalogueValueId.eq(catalogueValueId));
        where.and(statusPredicate(Status.ACTIVE));
        return where;
    }

    /**
     * Status predicate
     *
     * @param status Status
     * @return Predicate
     */
    private Predicate statusPredicate(Status status) {
        return catalogueValueEntity.status.eq(status.value);
    }

    /**
     * Builder query
     *
     * @param query Query
     * @return Builder boolean query
     */
    private BooleanBuilder buildQuery(QueryCatalogueValue query) {
        BooleanBuilder where = new BooleanBuilder();

        // Add status active condition
        where.and(catalogueValueEntity.status.eq(Status.ACTIVE.value));

        // Filter by catalogueTypeId
        if (query.getCatalogueTypeId() != null) {
            where.and(catalogueValueEntity.catalogueTypeId.eq(query.getCatalogueTypeId()));
        }

        return where;
    }

    /**
     * Builder query
     *
     * @param code String
     * @return List of Catalogs
     */
    public List<CatalogueValueVo> findAllByCode(String code){
        return from(catalogueValueEntity)
                .leftJoin(catalogueTypeEntity).on(catalogueValueEntity.catalogueTypeId.eq(catalogueTypeEntity.catalogueTypeId))
                .where(catalogueTypeEntity.code.eq(code))
                .select(Projections.bean(CatalogueValueVo.class,
                    catalogueValueEntity.catalogueValueId,
                    catalogueValueEntity.code,
                    catalogueValueEntity.name))
                .orderBy(catalogueValueEntity.placement.asc())
                .fetch();
    }

}

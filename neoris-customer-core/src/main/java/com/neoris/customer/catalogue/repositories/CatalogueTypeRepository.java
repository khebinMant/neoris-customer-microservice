package com.neoris.customer.catalogue.repositories;

import com.neoris.customer.catalogue.entities.CatalogueTypeEntity;
import com.neoris.customer.catalogue.entities.QCatalogueTypeEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.CreateCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueType;
import com.neoris.customer.catalogue.vo.CatalogueType.UpdateCatalogueType;
import com.neoris.customer.common.repositories.JPAQueryDslBaseRepository;
import com.neoris.customer.common.enums.Status;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Lazy
@Repository
public class CatalogueTypeRepository extends JPAQueryDslBaseRepository<CatalogueTypeEntity> implements ICatalogueTypeRepository {

    public CatalogueTypeRepository() {
        super(CatalogueTypeEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueTypeEntity create(CreateCatalogueType createCatalogueType) {
        CatalogueTypeEntity entity = CatalogueTypeEntity.builder()
            .code(createCatalogueType.getCode())
            .name(createCatalogueType.getName())
            .description(createCatalogueType.getDescription())
            .build();

        this.save(entity);

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogueTypeEntity findById(Long catalogueTypeId) {
        return from(QCatalogueTypeEntity.catalogueTypeEntity)
            .where(this.activeCatalogueCondition(catalogueTypeId))
            .fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntity(Long catalogueTypeId, UpdateCatalogueType updateCatalogueType) {
        this.update(QCatalogueTypeEntity.catalogueTypeEntity)
            .where(this.activeCatalogueCondition(catalogueTypeId))
            .set(QCatalogueTypeEntity.catalogueTypeEntity.name, updateCatalogueType.getName())
            .set(QCatalogueTypeEntity.catalogueTypeEntity.description, updateCatalogueType.getDescription())
            .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inactive(Long catalogueTypeId) {
        this.update(QCatalogueTypeEntity.catalogueTypeEntity)
            .where(this.activeCatalogueCondition(catalogueTypeId))
            .set(QCatalogueTypeEntity.catalogueTypeEntity.status, Status.INACTIVE.value)
            .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatalogueTypeEntity> findAll(QueryCatalogueType query) {
        return from(QCatalogueTypeEntity.catalogueTypeEntity)
            .where(this.buildQuery(query))
            .fetch();
    }

    /**
     * Active catalogue condition
     *
     * @param catalogueTypeId Catalogue Id
     * @return BooleanBuilder
     */
    private BooleanBuilder activeCatalogueCondition(Long catalogueTypeId) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(QCatalogueTypeEntity.catalogueTypeEntity.catalogueTypeId.eq(catalogueTypeId));
        where.and(statusPredicate(Status.ACTIVE));
        return where;
    }

    /**
     * Generate predicate status
     *
     * @param status Status
     * @return Predicate
     */
    private Predicate statusPredicate(Status status) {
        return QCatalogueTypeEntity.catalogueTypeEntity.status.eq(status.value);
    }

    /**
     * Build query
     *
     * @param queryCatalogueType QueryCatalogueType
     * @return BooleanBuilder
     */
    private BooleanBuilder buildQuery(QueryCatalogueType queryCatalogueType) {
        BooleanBuilder where = new BooleanBuilder();

        // Add status active condition
        where.and(statusPredicate(Status.ACTIVE));

        // Add code condition
        if (StringUtils.isNotBlank(queryCatalogueType.getCode())) {
            where = where.and(QCatalogueTypeEntity.catalogueTypeEntity.code.eq(queryCatalogueType.getCode()));
        }

        // Add name condition
        if (StringUtils.isNotBlank(queryCatalogueType.getName())) {
            where = where.and(QCatalogueTypeEntity.catalogueTypeEntity.name.containsIgnoreCase(queryCatalogueType.getName().trim()));
        }

        return where;
    }

}

package com.neoris.customer.catalogue.repositories;

import com.neoris.customer.catalogue.entities.CatalogueValueEntity;
import com.neoris.customer.catalogue.entities.QCatalogueTypeEntity;
import com.neoris.customer.catalogue.entities.QCatalogueValueEntity;
import com.neoris.customer.catalogue.vo.CatalogueType.QueryCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.CatalogueValueVo;
import com.neoris.customer.catalogue.vo.CatalogueValue.CreateCatalogueValue;
import com.neoris.customer.catalogue.vo.CatalogueValue.UpdateCatalogueValue;
import com.neoris.customer.common.repositories.JPAQueryDslBaseRepository;
import com.neoris.customer.common.enums.Status;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return from(QCatalogueValueEntity.catalogueValueEntity)
            .select(bean(CatalogueValueEntity.class, QCatalogueValueEntity.catalogueValueEntity.catalogueValueId, QCatalogueValueEntity.catalogueValueEntity.code, QCatalogueValueEntity.catalogueValueEntity.name, QCatalogueValueEntity.catalogueValueEntity.description))
            .where(this.activeCatalogueCondition(catalogueValueId))
            .fetchFirst();
    }

    @Override
    public CatalogueValueEntity findByCode(String code) {
        return from(QCatalogueValueEntity.catalogueValueEntity)
            .where(QCatalogueValueEntity.catalogueValueEntity.code.eq(code))
            .fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntity(Long catalogueValueId, UpdateCatalogueValue updateCatalogueValue) {
        this.update(QCatalogueValueEntity.catalogueValueEntity)
            .where(this.activeCatalogueCondition(catalogueValueId))
            .set(QCatalogueValueEntity.catalogueValueEntity.name, updateCatalogueValue.getName())
            .set(QCatalogueValueEntity.catalogueValueEntity.description, updateCatalogueValue.getDescription())
            .set(QCatalogueValueEntity.catalogueValueEntity.placement, updateCatalogueValue.getPlacement())
            .set(QCatalogueValueEntity.catalogueValueEntity.isDefault, updateCatalogueValue.getIsDefault())
            .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inactive(Long catalogueValueId) {
        this.update(QCatalogueValueEntity.catalogueValueEntity)
            .where(this.activeCatalogueCondition(catalogueValueId))
            .set(QCatalogueValueEntity.catalogueValueEntity.status, Status.INACTIVE.value)
            .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatalogueValueEntity> findAll(QueryCatalogueValue query) {
        return from(QCatalogueValueEntity.catalogueValueEntity)
            .where(buildQuery(query))
            .orderBy(QCatalogueValueEntity.catalogueValueEntity.placement.asc())
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
        where.and(QCatalogueValueEntity.catalogueValueEntity.catalogueValueId.eq(catalogueValueId));
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
        return QCatalogueValueEntity.catalogueValueEntity.status.eq(status.value);
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
        where.and(QCatalogueValueEntity.catalogueValueEntity.status.eq(Status.ACTIVE.value));

        // Filter by catalogueTypeId
        if (query.getCatalogueTypeId() != null) {
            where.and(QCatalogueValueEntity.catalogueValueEntity.catalogueTypeId.eq(query.getCatalogueTypeId()));
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
        return from(QCatalogueValueEntity.catalogueValueEntity)
                .leftJoin(QCatalogueTypeEntity.catalogueTypeEntity).on(QCatalogueValueEntity.catalogueValueEntity.catalogueTypeId.eq(QCatalogueTypeEntity.catalogueTypeEntity.catalogueTypeId))
                .where(QCatalogueTypeEntity.catalogueTypeEntity.code.eq(code))
                .select(Projections.bean(CatalogueValueVo.class,
                    QCatalogueValueEntity.catalogueValueEntity.catalogueValueId,
                    QCatalogueValueEntity.catalogueValueEntity.code,
                    QCatalogueValueEntity.catalogueValueEntity.name))
                .orderBy(QCatalogueValueEntity.catalogueValueEntity.placement.asc())
                .fetch();
    }

}

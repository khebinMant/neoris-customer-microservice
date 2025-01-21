package com.neoris.customer.catalogue.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCatalogueValueEntity is a Querydsl query type for CatalogueValueEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCatalogueValueEntity extends EntityPathBase<CatalogueValueEntity> {

    private static final long serialVersionUID = 161588896L;

    public static final QCatalogueValueEntity catalogueValueEntity = new QCatalogueValueEntity("catalogueValueEntity");

    public final com.neoris.customer.common.entities.QAbstractBaseAuditable _super = new com.neoris.customer.common.entities.QAbstractBaseAuditable(this);

    public final NumberPath<Long> catalogueTypeId = createNumber("catalogueTypeId", Long.class);

    public final NumberPath<Long> catalogueValueId = createNumber("catalogueValueId", Long.class);

    public final StringPath code = createString("code");

    //inherited
    public final NumberPath<Long> createdByUser = _super.createdByUser;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdFromIp = _super.createdFromIp;

    public final StringPath description = createString("description");

    public final StringPath isDefault = createString("isDefault");

    //inherited
    public final NumberPath<Long> lastModifiedByUser = _super.lastModifiedByUser;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final NumberPath<Long> placement = createNumber("placement", Long.class);

    //inherited
    public final StringPath status = _super.status;

    //inherited
    public final StringPath updatedFromIp = _super.updatedFromIp;

    public QCatalogueValueEntity(String variable) {
        super(CatalogueValueEntity.class, forVariable(variable));
    }

    public QCatalogueValueEntity(Path<? extends CatalogueValueEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatalogueValueEntity(PathMetadata metadata) {
        super(CatalogueValueEntity.class, metadata);
    }

}


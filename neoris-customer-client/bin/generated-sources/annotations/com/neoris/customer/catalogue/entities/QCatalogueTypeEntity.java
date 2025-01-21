package com.neoris.customer.catalogue.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCatalogueTypeEntity is a Querydsl query type for CatalogueTypeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCatalogueTypeEntity extends EntityPathBase<CatalogueTypeEntity> {

    private static final long serialVersionUID = 313475153L;

    public static final QCatalogueTypeEntity catalogueTypeEntity = new QCatalogueTypeEntity("catalogueTypeEntity");

    public final com.neoris.customer.common.entities.QAbstractBaseAuditable _super = new com.neoris.customer.common.entities.QAbstractBaseAuditable(this);

    public final NumberPath<Long> catalogueTypeId = createNumber("catalogueTypeId", Long.class);

    public final StringPath code = createString("code");

    //inherited
    public final NumberPath<Long> createdByUser = _super.createdByUser;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdFromIp = _super.createdFromIp;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> lastModifiedByUser = _super.lastModifiedByUser;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath status = _super.status;

    //inherited
    public final StringPath updatedFromIp = _super.updatedFromIp;

    public QCatalogueTypeEntity(String variable) {
        super(CatalogueTypeEntity.class, forVariable(variable));
    }

    public QCatalogueTypeEntity(Path<? extends CatalogueTypeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatalogueTypeEntity(PathMetadata metadata) {
        super(CatalogueTypeEntity.class, metadata);
    }

}


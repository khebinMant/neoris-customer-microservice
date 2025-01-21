package com.neoris.customer.common.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractBaseAuditable is a Querydsl query type for AbstractBaseAuditable
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAbstractBaseAuditable extends EntityPathBase<AbstractBaseAuditable> {

    private static final long serialVersionUID = 2040846501L;

    public static final QAbstractBaseAuditable abstractBaseAuditable = new QAbstractBaseAuditable("abstractBaseAuditable");

    public final NumberPath<Long> createdByUser = createNumber("createdByUser", Long.class);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath createdFromIp = createString("createdFromIp");

    public final NumberPath<Long> lastModifiedByUser = createNumber("lastModifiedByUser", Long.class);

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath status = createString("status");

    public final StringPath updatedFromIp = createString("updatedFromIp");

    public QAbstractBaseAuditable(String variable) {
        super(AbstractBaseAuditable.class, forVariable(variable));
    }

    public QAbstractBaseAuditable(Path<? extends AbstractBaseAuditable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractBaseAuditable(PathMetadata metadata) {
        super(AbstractBaseAuditable.class, metadata);
    }

}


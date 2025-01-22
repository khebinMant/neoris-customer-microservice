package com.neoris.customer.person.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonEntity is a Querydsl query type for PersonEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonEntity extends EntityPathBase<PersonEntity> {

    private static final long serialVersionUID = 791254111L;

    public static final QPersonEntity personEntity = new QPersonEntity("personEntity");

    public final com.neoris.customer.common.entities.QAbstractBaseAuditable _super = new com.neoris.customer.common.entities.QAbstractBaseAuditable(this);

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> birthDate = createDateTime("birthDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> createdByUser = _super.createdByUser;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdFromIp = _super.createdFromIp;

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> identityNumber = createNumber("identityNumber", Long.class);

    //inherited
    public final NumberPath<Long> lastModifiedByUser = _super.lastModifiedByUser;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final NumberPath<Long> personId = createNumber("personId", Long.class);

    public final StringPath phone = createString("phone");

    //inherited
    public final StringPath status = _super.status;

    public final StringPath surname = createString("surname");

    //inherited
    public final StringPath updatedFromIp = _super.updatedFromIp;

    public QPersonEntity(String variable) {
        super(PersonEntity.class, forVariable(variable));
    }

    public QPersonEntity(Path<? extends PersonEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonEntity(PathMetadata metadata) {
        super(PersonEntity.class, metadata);
    }

}


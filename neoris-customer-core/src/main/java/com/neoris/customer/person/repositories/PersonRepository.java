package com.neoris.customer.person.repositories;

import com.neoris.customer.common.repositories.JPAQueryDslBaseRepository;
import com.neoris.customer.person.entities.QPersonEntity;
import com.neoris.customer.person.vo.CreatePersonVo;
import com.neoris.customer.person.entities.PersonEntity;
import com.neoris.customer.common.enums.Status;
import com.neoris.customer.person.vo.UpdatePersonVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * {@inheritDoc}
 */
@Repository
public class PersonRepository  extends JPAQueryDslBaseRepository<PersonEntity> implements IPersonRepository  {
    public PersonRepository(){
        super(PersonEntity.class);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PersonEntity create(CreatePersonVo createPersonVo) {
        PersonEntity entity = PersonEntity.builder()
                .name(createPersonVo.getName())
                .surname(createPersonVo.getSurname())
                .gender(createPersonVo.getGender())
                .birthDate(createPersonVo.getBirthDate())
                .identityNumber(createPersonVo.getIdentityNumber())
                .address(createPersonVo.getAddress())
                .phone(createPersonVo.getPhone())
                .status(createPersonVo.getStatus())
                .createdByUser(createPersonVo.getCreatedByUser())
                .lastModifiedDate(createPersonVo.getLastModifiedDate())
                .createdFromIp(createPersonVo.getCreatedFromIp())
                .updatedFromIp(createPersonVo.getUpdatedFromIp())
                .build();
        this.save(entity);
        return entity;
    }

    @Override
    public PersonEntity findById(Long personId) {
        return from(QPersonEntity.personEntity)
                .where(this.activePersonCondition(personId))
                .fetchFirst();
    }

    @Override
    public PersonEntity findByIdentificationNumber(Long identityNumber) {
        return from(QPersonEntity.personEntity)
                .where(QPersonEntity.personEntity.status.eq(Status.ACTIVE.value))
                .where(QPersonEntity.personEntity.identityNumber.eq(identityNumber))
                .fetchFirst();
    }

    @Override
    public void updatePerson(UpdatePersonVo updatePersonVo, Long personId) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, QPersonEntity.personEntity);
        updateClause.where(this.activePersonCondition(personId));

        if (updatePersonVo.getName() != null) {
            updateClause.set(QPersonEntity.personEntity.name, updatePersonVo.getName());
        }

        if (updatePersonVo.getSurname() != null) {
            updateClause.set(QPersonEntity.personEntity.surname, updatePersonVo.getSurname());
        }

        if (updatePersonVo.getBirthDate() != null) {
            updateClause.set(QPersonEntity.personEntity.birthDate, updatePersonVo.getBirthDate());
        }

        if (updatePersonVo.getIdentityNumber() != null) {
            updateClause.set(QPersonEntity.personEntity.identityNumber, updatePersonVo.getIdentityNumber());
        }

        if (updatePersonVo.getAddress() != null) {
            updateClause.set(QPersonEntity.personEntity.address, updatePersonVo.getAddress());
        }

        if (updatePersonVo.getPhone() != null) {
            updateClause.set(QPersonEntity.personEntity.phone, updatePersonVo.getPhone());
        }

        /*
         * Used to logical delete
         * */
        if (updatePersonVo.getStatus() != null) {
            updateClause.set(QPersonEntity.personEntity.status, updatePersonVo.getStatus());
        }

        updateClause.execute();
    }

    /**
     * Generate predicate status
     *
     * @param status Status
     * @return Predicate
     */
    private Predicate statusPredicate(Status status) {
        return QPersonEntity.personEntity.status.eq(status.value);
    }

    /**
     * Active Client condition
     *
     * @param personId Long
     * @return BooleanBuilder
     */
    private BooleanBuilder activePersonCondition(Long personId) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(QPersonEntity.personEntity.personId.eq(personId));
        where.and(QPersonEntity.personEntity.status.eq(Status.ACTIVE.value));
        return where;
    }
}

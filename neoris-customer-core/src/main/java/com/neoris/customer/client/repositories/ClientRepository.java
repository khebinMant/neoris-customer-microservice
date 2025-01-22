package com.neoris.customer.client.repositories;

import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.client.vo.UpdateClientVo;
import com.neoris.customer.common.enums.Status;
import com.neoris.customer.common.repositories.JPAQueryDslBaseRepository;
import com.neoris.customer.person.entities.PersonEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.neoris.customer.client.entities.QClientEntity.clientEntity;

    /**
     * {@inheritDoc}
     */
    @Repository
public class ClientRepository extends JPAQueryDslBaseRepository<ClientEntity> implements IClientRepository{

    public ClientRepository(){
        super(ClientEntity.class);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClientEntity create(CreateClientVo createClientVo, PersonEntity personEntity) {
        ClientEntity entity = ClientEntity.builder()
            .password(createClientVo.getPassword())
            .person(personEntity)
            .status(Status.ACTIVE.value)
            .createdByUser(createClientVo.getCreatedByUser())
            .lastModifiedDate(createClientVo.getLastModifiedDate())
            .createdFromIp(createClientVo.getCreatedFromIp())
            .updatedFromIp(createClientVo.getUpdatedFromIp())
            .build();
        this.save(entity);
        return entity;
    }

    @Override
    public ClientEntity findById(Long clientId) {
        return from(clientEntity)
                .where(this.activeClientCondition(clientId))
                .fetchFirst();
    }

    @Override
    public ClientEntity findByIdentificationPersonNumber(Long identityNumber) {
        return from(clientEntity)
                .where(clientEntity.status.eq(Status.ACTIVE.value))
                .where(clientEntity.person.status.eq(Status.ACTIVE.value))
                .where(clientEntity.person.identityNumber.eq(identityNumber))
                .fetchFirst();
    }

    @Override
    public void updateClient(UpdateClientVo updateClientVo, Long clientId) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, clientEntity);
        updateClause.where(this.activeClientCondition(clientId));

        if (updateClientVo.getPassword() != null) {
            updateClause.set(clientEntity.password, updateClientVo.getPassword());
        }

        /*
        * Used to logical delete
        * */
        if (updateClientVo.getStatus() != null) {
            updateClause.set(clientEntity.status, updateClientVo.getStatus());
        }

        updateClause.execute();
    }

        @Override
        public List<ClientEntity> findAllClients() {
            return from(clientEntity)
                    .where(clientEntity.status.eq(Status.ACTIVE.value))
                    .fetch();
        }

        /**
     * Generate predicate status
     *
     * @param status Status
     * @return Predicate
     */
    private Predicate statusPredicate(Status status) {
        return clientEntity.status.eq(status.value);
    }

    /**
     * Active Client condition
     *
     * @param clientId Long
     * @return BooleanBuilder
     */
    private BooleanBuilder activeClientCondition(Long clientId) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(clientEntity.clientId.eq(clientId));
        where.and(statusPredicate(Status.ACTIVE));
        return where;
    }
}

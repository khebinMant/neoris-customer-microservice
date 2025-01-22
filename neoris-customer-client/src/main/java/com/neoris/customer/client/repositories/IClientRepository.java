package com.neoris.customer.client.repositories;


import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.vo.UpdateClientVo;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.common.repositories.IQueryDslBaseRepository;
import com.neoris.customer.person.entities.PersonEntity;

import java.util.List;

/**
 * ClientRepository
 *
 * @author Kevin
 * @version 1.0
 */
public interface IClientRepository extends IQueryDslBaseRepository<ClientEntity> {

    /**
     * Create Client.
     *
     * @author Kevin on 20/01/2025
     * @param createClient CreateClient
     * @param personEntity PersonEntity
     * @return ClientEntity
     */
    ClientEntity create(CreateClientVo createClient, PersonEntity personEntity);

    /**
     * Get a Client given an ID.
     *
     * @author Kevin on 20/01/2025
     * @return a ClientEntity
     */
    ClientEntity findById(Long clientId);

    /**
     * Get a Client given an Identification Person.
     *
     * @author Kevin on 20/01/2025
     * @param identityNumber Long
     * @return ClientEntity
     */
    ClientEntity findByIdentificationPersonNumber(Long identityNumber);

    /**
     * Update Client
     * is also used to inactivate (delete)
     * (logical delete not physically delete)
     * @author Kevin on 20/01/2025
     * @param updateClientVo UpdateClient
     * @param clientId Long
     */
    void updateClient(UpdateClientVo updateClientVo, Long clientId);

    List<ClientEntity> findAllClients();
}

package com.neoris.customer.client.services;

import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.vo.UpdateClientVo;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.common.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * ClientService
 *
 * @author Kevin
 * @version 1.0
 */
public interface IClientService {

    /**
     * Create Client.
     *
     * @author Kevin on 20/01/2025
     * @param createClient CreateClient
     * @return ClientEntity
     */
    ClientEntity create(CreateClientVo createClient) throws EntityNotFoundException;

    /**
     * Get a Client given an ID.
     *
     * @author Kevin on 20/01/2025
     * @return a ClientEntity
     */
    ClientEntity findById(Long clientId) throws EntityNotFoundException;

    /**
     * Get a Client given an Identification Person.
     *
     * @author Kevin on 20/01/2025
     * @param identityNumber Long
     * @return ClientEntity
     */
    ClientEntity findByIdentificationPersonNumber(Long identityNumber) throws EntityNotFoundException;

    /**
     * Update Client
     * is also used to inactivate (delete)
     * (logical delete not physically delete)
     * @author Kevin on 20/01/2025
     * @param updateClientVo UpdateClient
     */
    void updateClient(UpdateClientVo updateClientVo, Long clientId) throws EntityNotFoundException;

    /**
     * Find all Client
     *
     * @author Kevin on 20/01/2025
     * @return List of ClientEntity
     */
    List<ClientEntity> findAllClients();
}

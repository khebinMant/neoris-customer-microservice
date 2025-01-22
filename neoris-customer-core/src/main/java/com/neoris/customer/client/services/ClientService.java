package com.neoris.customer.client.services;

import com.neoris.customer.client.entities.ClientEntity;
import com.neoris.customer.client.vo.UpdateClientVo;
import com.neoris.customer.client.repositories.IClientRepository;
import com.neoris.customer.client.vo.CreateClientVo;
import com.neoris.customer.common.exceptions.EntityNotFoundException;
import com.neoris.customer.person.services.IPersonService;
import com.neoris.customer.person.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Validated
@Lazy
@Service
@Transactional
public class ClientService implements IClientService{

    @Lazy
    @Autowired
    private IClientRepository clientRepository;

    @Lazy
    @Autowired
    private IPersonService personService;

    @Override
    public ClientEntity create(CreateClientVo createClient) throws EntityNotFoundException{
        PersonEntity foundedPerson =  personService.findByIdentificationNumber(createClient.getIdentityNumber());

        return clientRepository.create(createClient, foundedPerson);
    }

    @Override
    public ClientEntity findById(Long clientId) throws EntityNotFoundException{
        return Optional.ofNullable(clientRepository.findById(clientId))
                .orElseThrow(() -> new EntityNotFoundException("No existe el cliente con el id %s".formatted(clientId)));
    }

    @Override
    public ClientEntity findByIdentificationPersonNumber(Long identityNumber) throws EntityNotFoundException{
        return Optional.ofNullable(clientRepository.findByIdentificationPersonNumber(identityNumber))
                .orElseThrow(() -> new EntityNotFoundException("No existe el cliente con el número de cédula %s".formatted(identityNumber)));
    }

    @Override
    public void updateClient(UpdateClientVo updateClientVo, Long clientId) throws EntityNotFoundException{
        this.findById(clientId);
        clientRepository.updateClient(updateClientVo, clientId);
    }

    @Override
    public List<ClientEntity> findAllClients() {
        return clientRepository.findAllClients();
    }
}

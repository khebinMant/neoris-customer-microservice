package com.neoris.customer.person.services;

import com.neoris.customer.person.vo.CreatePersonVo;
import com.neoris.customer.person.entities.PersonEntity;
import com.neoris.customer.common.exceptions.EntityNotFoundException;
import com.neoris.customer.person.repositories.IPersonRepository;
import com.neoris.customer.person.vo.UpdatePersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Validated
@Lazy
@Service
@Transactional
public class PersonService implements IPersonService {

    @Lazy
    @Autowired
    private IPersonRepository personRepository;


    @Override
    public PersonEntity create(CreatePersonVo createPersonVo) throws EntityNotFoundException {

        return personRepository.create(createPersonVo);
    }

    @Override
    public PersonEntity findById(Long personId) throws EntityNotFoundException {
        return Optional.ofNullable(personRepository.findById(personId))
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con el id %s".formatted(personId)));
    }

    @Override
    public PersonEntity findByIdentificationNumber(Long identityNumber) throws EntityNotFoundException {
        return Optional.ofNullable(personRepository.findByIdentificationNumber(identityNumber))
                .orElseThrow(() -> new EntityNotFoundException("No existe la persona con la cédula %s".formatted(identityNumber)));
    }

    @Override
    public void updatePerson(UpdatePersonVo updatePersonVo, Long personId) throws EntityNotFoundException {
        this.findById(personId);
        personRepository.updatePerson(updatePersonVo, personId);
    }
}

package com.neoris.customer.person.services;

import com.neoris.customer.common.exceptions.EntityNotFoundException;
import com.neoris.customer.person.entities.PersonEntity;
import com.neoris.customer.person.vo.CreatePersonVo;
import com.neoris.customer.person.vo.UpdatePersonVo;

/**
 * PersonService
 *
 * @author Kevin
 * @version 1.0
 */
public interface IPersonService {
    /**
     * Create Person.
     *
     * @author Kevin on 20/01/2025
     * @param createPerson CreatePerson
     * @return PersonEntity
     */
    PersonEntity create(CreatePersonVo createPerson) throws EntityNotFoundException;

    /**
     * Get a Person given an ID.
     *
     * @author Kevin on 20/01/2025
     * @param personId Long
     * @return a PersonEntity
     */
    PersonEntity findById(Long personId) throws EntityNotFoundException;

    /**
     * Get a Person given an Identification Person.
     *
     * @author Kevin on 20/01/2025
     * @param identityNumber Long
     * @return PersonEntity
     */
    PersonEntity findByIdentificationNumber(Long identityNumber) throws EntityNotFoundException;

    /**
     * Update Person
     * is also used to inactivate (delete)
     * (logical delete not physically delete)
     * @author Kevin on 20/01/2025
     * @param updatePersonVo UpdatePersonVo
     * @param personId Long
     */
    void updatePerson(UpdatePersonVo updatePersonVo, Long personId) throws EntityNotFoundException;
}

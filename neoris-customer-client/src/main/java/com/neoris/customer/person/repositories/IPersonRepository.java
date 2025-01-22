package com.neoris.customer.person.repositories;

import com.neoris.customer.person.vo.CreatePersonVo;
import com.neoris.customer.person.vo.UpdatePersonVo;
import com.neoris.customer.common.repositories.IQueryDslBaseRepository;
import com.neoris.customer.person.entities.PersonEntity;


/**
 * PersonRepository
 *
 * @author Kevin
 * @version 1.0
 */
public interface IPersonRepository extends IQueryDslBaseRepository<PersonEntity> {

    /**
     * Create Person.
     *
     * @author Kevin on 20/01/2025
     * @param createPerson CreatePerson
     * @return PersonEntity
     */
    PersonEntity create(CreatePersonVo createPerson);

    /**
     * Get a Person given an ID.
     *
     * @author Kevin on 20/01/2025
     * @return a PersonEntity
     */
    PersonEntity findById(Long personId);

    /**
     * Get a Person given an Identification Person.
     *
     * @author Kevin on 20/01/2025
     * @param identificationNumber Long
     * @return PersonEntity
     */
    PersonEntity findByIdentificationNumber(Long identityNumber);

    /**
     * Update Person
     * is also used to inactivate (delete)
     * (logical delete not physically delete)
     * @author Kevin on 20/01/2025
     * @param person UpdatePerson
     * @param personId Long
     */
    void updatePerson(UpdatePersonVo person, Long personId);
}

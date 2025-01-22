package com.neoris.customer.person.entities;

import com.neoris.customer.common.entities.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/*
 * All the Auditory fields are in the AbstractBaseAuditable class
 * included status as a string value 1 to active 0 inactive
 * */
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table( name = "person", schema = "customer")
public class PersonEntity extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false, updatable = false)
    private Long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "identity_number")
    private Long identityNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}

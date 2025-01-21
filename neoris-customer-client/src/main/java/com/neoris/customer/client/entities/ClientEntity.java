package com.neoris.customer.client;

import com.neoris.customer.person.PersonEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
 * All the Auditory fields are in the AbstractBaseAuditable class
 * included status as a string value 1 to active 0 inactive
 *  in this case Person Entity al ready extends AbstractBaseAuditable
 * */
@Data
@SuperBuilder
@NoArgsConstructor
@Entity( name = "client")
@Table( schema = "customer")
@EqualsAndHashCode(callSuper = true)
public class ClientEntity extends PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, updatable = false)
    private Long clientId;

    @Column(name = "password")
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}

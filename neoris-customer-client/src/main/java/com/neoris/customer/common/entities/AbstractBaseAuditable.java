package com.neoris.customer.common.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class AbstractBaseAuditable {
    @Column(name = "status", length = 1)
    protected String status;

    @CreatedBy
    @Column(name = "created_by_user", updatable = false)
    protected Long createdByUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    protected Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by_user")
    protected Long lastModifiedByUser;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    protected Date lastModifiedDate;

    @Column(name = "created_from_ip")
    protected String createdFromIp;

    @Column(name = "updated_from_ip")
    protected String updatedFromIp;
}
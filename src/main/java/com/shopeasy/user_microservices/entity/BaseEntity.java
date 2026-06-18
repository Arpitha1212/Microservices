package com.shopeasy.user_microservices.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name="CREATED_AT", nullable = false,updatable = false)
    private Instant createdAt;

    @CreatedBy
    @Column(name="CREATED_BY", nullable = false,length = 20,updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name="UPDATED_AT", nullable = true,insertable = false)
    private Instant updatedAt;

    @LastModifiedBy
    @Column(name="UPDATED_BY", nullable = true,length = 20,insertable = false)
    private String updatedBy;

}

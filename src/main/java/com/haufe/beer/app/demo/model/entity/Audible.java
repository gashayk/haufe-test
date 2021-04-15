package com.haufe.beer.app.demo.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audible {

    @CreatedDate
    private LocalDateTime creationDate;

    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modificationDate;

    private String modifiedBy;
}

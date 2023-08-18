package com.development.ssosung.domain.util;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

//    @CreatedBy
//    @Column(name = "CRE_USER")
//    private String creUser;

    @CreatedDate
    @Column(name = "CRE_DTM")
    private LocalDateTime creDtm;

//    @LastModifiedBy
//    @Column(name = "UPD_USER")
//    private String updUser;

    @LastModifiedDate
    @Column(name = "UPD_DTM")
    private LocalDateTime updDtm;
}

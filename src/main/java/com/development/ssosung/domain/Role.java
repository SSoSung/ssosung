package com.development.ssosung.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
}

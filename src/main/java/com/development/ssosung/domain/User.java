package com.development.ssosung.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
@Entity
public class User {

    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PW", length = 500)
    private String userPw;

    @Column(name = "USER_NM", length = 50)
    private String userNm;

    @Column(name = "USER_TEL", length = 50)
    private String userTel;

    @Column(name = "USER_ROLE", length = 10)
    private String userRole;

    @Builder
    public User(String userId, String userPw, String userNm, String userTel, String userRole) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.userTel = userTel;
        this.userRole = userRole;
    }
}

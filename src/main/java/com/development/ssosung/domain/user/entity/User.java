package com.development.ssosung.domain.user.entity;

import com.development.ssosung.domain.user.entity.id.UserId;
import com.development.ssosung.domain.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserId.class)
@Table(name ="USER")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PW", length = 255)
    private String userPw;

    @Column(name = "USER_NM", length = 30)
    private String userNm;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "CELL_PHONE", length = 50)
    private String cellPhone;

    @Column(name = "USER_ROLE", length = 10)
    private String userRole;

    @Column(name = "USE_YN", length = 1)
    private String useYn;


    @Builder
    public User(String userId, String userPw, String userNm, String email, String cellPhone, String userRole, String useYn) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.email = email;
        this.cellPhone = cellPhone;
        this.userRole = userRole;
        this.useYn = useYn;
    }
}

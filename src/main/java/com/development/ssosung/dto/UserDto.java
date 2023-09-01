package com.development.ssosung.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {

    @Setter
    @Getter
    @NoArgsConstructor
    public static class UserSaveRequest{

        private String userId;
        private String userPw;
        private String userNm;
        private String userTel;
    }
}

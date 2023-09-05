package com.development.ssosung.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @Setter
    @Getter
    public static class UserCreateRequest{

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userId;

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userPw;

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userNm;

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userTel;
    }

    @Setter
    @Getter
    public static class UserReadRequest{

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userId;
    }

    @Builder
    public static class UserReadResponse{

        private String userId;
        private String userNm;
        private String userTel;

    }

    @Setter
    @Getter
    public static class UserUpdateRequest{

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userId;

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userNm;

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userTel;
    }

    @Setter
    @Getter
    public static class UserDeleteRequest{

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String userId;

    }
}

package com.development.ssosung.domain.user.dto;

import com.development.ssosung.domain.user.entity.User;
import com.development.ssosung.domain.util.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request{

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response{

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignUpRequest{

        @NotBlank(message = "공백이 존재할 수 없습니다.")
        private String userId;

        @NotBlank(message = "공백이 존재할 수 없습니다.")
        private String userPw;

        @NotBlank(message = "공백이 존재할 수 없습니다.")
        private String userNm;

        @NotBlank(message = "공백이 존재할 수 없습니다.")
        private String email;

        @NotBlank(message = "공백이 존재할 수 없습니다.")
        private String cellPhone;

    }
}

package com.development.ssosung.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class EmailDto {

    @Setter
    @Getter
    public static class EmailReadRequest{

        @NotBlank(message = "빈값 또는 공백을 허용하지 않습니다.")
        private String email;

    }

}

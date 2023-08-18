package com.development.ssosung.domain.user.service;

import com.development.ssosung.domain.user.dto.UserDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserServ {

    void signUp(UserDto.SignUpRequest request);
}

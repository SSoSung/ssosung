package com.development.ssosung.domain.user.controller;

import com.development.ssosung.domain.user.dto.UserDto;
import com.development.ssosung.domain.user.service.UserServ;
import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.global.common.SsoSungStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserCont {

    private final UserServ userServ;

    @PostMapping("/sign-up")
    public ResponseEntity<SsoSungApiResponse> signUp(@RequestBody @Valid UserDto.SignUpRequest request){

        userServ.signUp(request);

        return ResponseEntity.ok().body(new SsoSungApiResponse(SsoSungStatus.CREATED, "회원가입완료"));
    }
}

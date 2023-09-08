package com.development.ssosung.api;

import com.development.ssosung.dto.EmailDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"회원가입시 이메일 인증번호 발송"}, description = "이메일 번호 인증")
@RequiredArgsConstructor
@RequestMapping("/api/email")
@RestController
public class EmailResource {

    private final EmailService emailService;

    @ApiOperation(value="이메일 인증번호 생성", notes="이메일에 인증번호를 발송한다. return 랜덤값과 사용자의 입력값이 같으면 통과")
    @GetMapping
    public ResponseEntity<SsoSungApiResponse> readEmail(@Valid @RequestBody EmailDto.EmailReadRequest request){
        return emailService.readEmail(request);
    }
}

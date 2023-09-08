package com.development.ssosung.service;

import com.development.ssosung.dto.EmailDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<SsoSungApiResponse> readEmail(EmailDto.EmailReadRequest request);
}

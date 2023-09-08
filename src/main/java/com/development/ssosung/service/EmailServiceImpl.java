package com.development.ssosung.service;

import com.development.ssosung.dto.EmailDto;
import com.development.ssosung.global.common.SsoSungApiResponse;
import com.development.ssosung.global.common.SsoSungStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "gustjd1209@gmail.com";
    private static int certificationNumber;


    @Override
    public ResponseEntity<SsoSungApiResponse> readEmail(EmailDto.EmailReadRequest request) {

        MimeMessage message = createEmail(request.getEmail());

        javaMailSender.send(message);

        SsoSungApiResponse apiResponse = new SsoSungApiResponse(SsoSungStatus.OK, "인증번호발송완료", String.valueOf(certificationNumber));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public MimeMessage createEmail(String mail){
        CreateCertificationNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + certificationNumber + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public void CreateCertificationNumber(){
        certificationNumber = (int)(Math.random() * (90000)) + 100000;
    }

}

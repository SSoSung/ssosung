package com.development.ssosung.domain.member.controller;

import com.development.ssosung.domain.member.service.MemeberServ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemeberCont {

    private final MemeberServ memeberServ;

}

package com.development.ssosung.domain.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}

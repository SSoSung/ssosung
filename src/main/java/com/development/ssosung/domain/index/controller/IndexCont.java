package com.development.ssosung.domain.index.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont {


    @ApiOperation(value="indexPage", notes="indexPage")
    @GetMapping("/")
    public String index(){
        return "index";
    }
}

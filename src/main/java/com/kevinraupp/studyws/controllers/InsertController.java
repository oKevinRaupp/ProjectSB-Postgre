package com.kevinraupp.studyws.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsertController {
    @GetMapping("/insert")
    public String users(){
        return "insert";
    }
}

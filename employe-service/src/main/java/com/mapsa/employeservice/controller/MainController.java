package com.mapsa.employeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("employe")
public class MainController {

    @GetMapping
    public Date getDate(){
        return new Date();
    }


}

package com.crewsync.EMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping
    public String helloMsg(){
        return "Welcome to Crewsync backend...!";
    }
}

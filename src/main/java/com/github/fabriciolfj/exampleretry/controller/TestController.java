package com.github.fabriciolfj.exampleretry.controller;

import com.github.fabriciolfj.exampleretry.dto.Test;
import com.github.fabriciolfj.exampleretry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public Test find() {
        return testService.process();
    }
}

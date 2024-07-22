package com.limyel.haoyuan.platform.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.limyel.haoyuan.platform.application.vo.Person;
import com.limyel.haoyuan.platform.core.enhancer.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final GlobalExceptionHandler globalExceptionHandler;
    @GetMapping
    public Person person() {
        Person person = new Person();
        person.setName("test");
        person.setBirthday(new Date());
        person.setCreateTime(LocalDateTime.now());
        if (person != null) {
            throw new RuntimeException();
        }
        return person;
    }
}

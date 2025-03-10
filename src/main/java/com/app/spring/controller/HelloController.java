package com.app.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Controller is used for routing purposes like for getting the request and serving with response.
// it acts as a controller with @Component annotation +
// it is having an @ResponseBody annotation so that each and every method returns some response in the body.
public class HelloController {

    @Value(("${welcome.message}"))
    private String welcomeMessage;

    @GetMapping(value = "/")
    public String helloWorld() {
        return welcomeMessage;
    }
}

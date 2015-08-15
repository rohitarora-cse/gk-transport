package com.gk.enterprise.transport.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public @ResponseBody Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Greeting greeting = new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    	return greeting;
    }
}

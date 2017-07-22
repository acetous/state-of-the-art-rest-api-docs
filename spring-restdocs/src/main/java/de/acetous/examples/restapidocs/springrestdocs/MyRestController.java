package de.acetous.examples.restapidocs.springrestdocs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MyRestController {

    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/time")
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public String sayMyName(@RequestParam("name") String name) {
        return "Your name is " + name + ".";
    }
}

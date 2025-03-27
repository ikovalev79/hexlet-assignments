package exercise.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
import exercise.daytime.Daytime;

@RestController
public class WelcomeController {
	
	@Autowired
    private Daytime daytime;

    @GetMapping(path = "")
    public String welcome() {
        return "Welcome to Spring";
    }
    
    @GetMapping(path = "/welcome")
    public String welcomeDaytime() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }
}
// END
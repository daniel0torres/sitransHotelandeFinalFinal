package co.edu.uniandes.hotelandes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController{
    @GetMapping(value="/hello")
    public String index() {
        return "HOLA";
    }
    
}
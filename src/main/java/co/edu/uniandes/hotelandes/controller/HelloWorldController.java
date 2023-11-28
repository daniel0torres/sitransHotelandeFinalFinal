package co.edu.uniandes.hotelandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.hotelandes.model.Hello;
import co.edu.uniandes.hotelandes.repository.HelloRepository;
import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    HelloRepository repo;

    @GetMapping(value = "/hello")
    public String index() {
        return "HOLA";
    }

    @GetMapping(value = "/hello2")
    public List<Hello> index2() {

        List<Hello> l = repo.findAll();
        return l;

    }
}

package co.edu.uniandes.hotelandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.hotelandes.model.Hello;
import co.edu.uniandes.hotelandes.repository.HelloRepository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.repository.query.Param;

@RestController
public class HelloController {

    
    
    @Autowired
    HelloRepository helloRepository;
    
    


    @GetMapping(value = "/hello2")
    public List<Hello> index2() {

        List<Hello> l = helloRepository.findAll();
        return l;

    }
    
    @GetMapping(value = "/create")
    public Hello crearHello(@Param(value = "name")String name, @Param(value = "edad") int edad){
        Hello h = helloRepository.insert(new Hello(name,edad));
        return h;
    }
    @GetMapping(value = "/find")
    public Hello crearHello(@Param(value = "id")String id){
        Hello hello = helloRepository.findById(new ObjectId(id)).orElse(null);
        return hello;
    }
}


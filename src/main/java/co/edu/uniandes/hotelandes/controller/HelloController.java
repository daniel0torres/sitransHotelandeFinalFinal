package co.edu.uniandes.hotelandes.controller;

import co.edu.uniandes.hotelandes.model.Hello;
import co.edu.uniandes.hotelandes.repository.HelloRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @Autowired
    HelloRepository helloRepository;

    
    @GetMapping("/hellos")
    public String read(Model model){
        model.addAttribute("hellos", helloRepository.findAll());
        return "hellos";
    }
    @GetMapping("/hellos/new")
    public String create(Model model) {
        model.addAttribute("hello", new Hello());
        return "helloNuevo";
    }

    @PostMapping("/hellos/new/save")
    public String createSave(@ModelAttribute Hello hello) {
        helloRepository.insert(new Hello(hello.getName(),hello.getEdad()));

        return "redirect:/hellos";
    }
    
    @GetMapping("/hellos/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Hello hello = helloRepository.findById(objectId).orElse(null);
        if (hello != null) {
            model.addAttribute("hello", new Hello());
            return "helloEditar";
        }

        else {
            return "redirect:/hellos";
        }

    }
    
    @PostMapping("/hellos/{id}/edit/save")
    public String updateSave(@PathVariable("id") String id, @ModelAttribute Hello hello) {
       ObjectId objectId = new ObjectId(id);
       hello.setId(objectId);
        helloRepository.save(hello);
        
        return "redirect:/hellos";
    }
    
    @GetMapping("/hellos/{id}/delete")
    public String delete(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        helloRepository.deleteById(objectId);
        return "redirect:/hellos";
    }
}

package co.edu.uniandes.hotelandes.controller;
import co.edu.uniandes.hotelandes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @Autowired
    ClienteRepository ClienteRepository;

    
    @GetMapping("/clientes")
    public String read(Model model){
        model.addAttribute("clientes", ClienteRepository.findAll());
        return "clientes";
    }
}
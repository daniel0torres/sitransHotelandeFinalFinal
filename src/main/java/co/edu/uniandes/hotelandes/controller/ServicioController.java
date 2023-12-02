package co.edu.uniandes.hotelandes.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.uniandes.hotelandes.model.Servicio;
import co.edu.uniandes.hotelandes.model.TipoHabitacion;
import co.edu.uniandes.hotelandes.repository.ServicioRepository;
import jakarta.annotation.PostConstruct;

@Controller
public class ServicioController {
    @Autowired
    ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String read(Model model) {
        model.addAttribute("servicios", servicioRepository.findAll());
        return "servicios";
    }

    @GetMapping("/servicios/new")
    public String create(Model model) {
        model.addAttribute("servicios", new Servicio());
        return "serviciosNew";
    }

    @PostMapping("/servicios/new/save")
    public String createSave(@ModelAttribute Servicio servicio) {
        servicioRepository.insert(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Servicio servicio = servicioRepository.findById(objectId).orElse(null);
        if (servicio != null) {
            model.addAttribute("servicio", new Servicio());
            return "serviciosEdit";
        }

        else {
            return "redirect:/servicio";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String updateSave(@PathVariable("id") String id, @ModelAttribute Servicio servicio) {
       ObjectId objectId = new ObjectId(id);
       servicio.set_id(objectId);
        servicioRepository.save(servicio);
        
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String delete(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        servicioRepository.deleteById(objectId);
        return "redirect:/servicios";
    }
}

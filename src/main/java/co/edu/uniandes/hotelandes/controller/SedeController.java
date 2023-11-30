package co.edu.uniandes.hotelandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.uniandes.hotelandes.model.Sede;
import co.edu.uniandes.hotelandes.repository.SedeRepository;

@Controller
public class SedeController {
    
    @Autowired
    SedeRepository sedeRepository;

    @GetMapping("/sedes")
    public String read(Model model) {
        model.addAttribute("sedes", sedeRepository.findAll());
        return "sedes";
    }

    @GetMapping("/sedes/new")
    public String create(Model model) {
        model.addAttribute("sedes", new Sede());
        return "sedesNew";
    }

    @PostMapping("/sedes/new/save")
    public String createSave(@ModelAttribute Sede sede) {
        sedeRepository.insert(sede);
        return "redirect:/sedes";
    }

    @GetMapping("/sedes/{nombre}/edit")
    public String update(@PathVariable String nombre, Model model) {
        Sede sede = sedeRepository.findById(nombre).orElse(null);
        if (sede != null) {
            model.addAttribute("sede", new Sede());
            return "sedesEdit";
        }
        else {
            return "redirect:/sedes";
        }
    }

    @PostMapping("/sedes/{nombre}/edit/save")
    public String updateSave(@PathVariable("nombre") String nombre, @ModelAttribute Sede sede) {
        sede.setNombre(nombre);
        sedeRepository.save(sede);
        return "redirect:/sedes";
    }

    @GetMapping("/sedes/{nombre}/delete")
    public String delete(@PathVariable String nombre) {
        sedeRepository.deleteById(nombre);
        return "redirect:/sedes";
    }
}

package co.edu.uniandes.hotelandes.controller;



import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.uniandes.hotelandes.model.Alojamiento;
import co.edu.uniandes.hotelandes.model.Habitacion;
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

    @GetMapping("/sedes/{sedeId}/habitaciones")
    public String obtenerHabitacionesDeSede(@PathVariable String sedeId, Model model) {
        ObjectId id;

        id = new ObjectId(sedeId);


        Optional<Sede> sedeOptional = sedeRepository.findById(id);
        Sede sede = sedeOptional.orElse(null);

        model.addAttribute("habitaciones", sede.getHabitaciones());
        return "habitaciones"; // Nombre del archivo de plantilla Thymeleaf
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

    @GetMapping("/sedes/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Sede sede = sedeRepository.findById(objectId).orElse(null);
        if (sede != null) {
            model.addAttribute("sede", new Sede());
            return "sedesEdit";
        }
        else {
            return "redirect:/sedes";
        }
    }


    @GetMapping("/sedes/{id}/delete")
    public String delete(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        sedeRepository.deleteById(objectId);
        return "redirect:/sedes";
    }
}

package co.edu.uniandes.hotelandes.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.uniandes.hotelandes.model.Reservas;
import co.edu.uniandes.hotelandes.repository.ReservasRepository;

@Controller
public class ReservasController {

    @Autowired
    ReservasRepository reservasRepository;

    @GetMapping("/reservas")
    public String read(Model model) {
        model.addAttribute("reservas", reservasRepository.findAll());
        return "reservas";
    }
    
    @GetMapping("/reservas/new")
    public String create(Model model) {
        model.addAttribute("reservas", new Reservas());
        return "reservasNew";
    }

    @PostMapping("/reservas/new/save")
    public String createSave(@ModelAttribute Reservas reserva) {
        reservasRepository.insert(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Reservas reserva = reservasRepository.findById(objectId).orElse(null);
        if (reserva != null) {
            model.addAttribute("reserva", new Reservas());
            return "reservaEdit";
        }

        else {
            return "redirect:/reservas";
        }
        
    }
}

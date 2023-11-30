package co.edu.uniandes.hotelandes.controller;
import co.edu.uniandes.hotelandes.model.TipoHabitacion;
import co.edu.uniandes.hotelandes.repository.TipoHabitacionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class TipoHabitacionController {

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

                  
    @GetMapping("/tiposHabitaciones")
    public String read(Model model){
        model.addAttribute("tiposHabitaciones", tipoHabitacionRepository.findAll());
        return "tiposHabitaciones";
    }
    @GetMapping("/tiposHabitaciones/new")
    public String create(Model model) {
        model.addAttribute("tipoHabitacion", new TipoHabitacion());
        return "tipoHabitacionNew";
    }

    @PostMapping("/tiposHabitaciones/new/save")
    public String createSave(@ModelAttribute TipoHabitacion tipoHabitacion) {
   
        tipoHabitacionRepository.insert(tipoHabitacion);

        return "redirect:/tiposHabitaciones";
    }
    
    @GetMapping("/tiposHabitaciones/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(objectId).orElse(null);
        if (tipoHabitacion != null) {
            model.addAttribute("tipoHabitacion", new TipoHabitacion());
            return "tipoHabitacionEdit";
        }

        else {
            return "redirect:/tiposHabitaciones";
        }

    }
    
    @PostMapping("/tiposHabitaciones/{id}/edit/save")
    public String updateSave(@PathVariable("id") String id, @ModelAttribute TipoHabitacion tipoHabitacion) {
       ObjectId objectId = new ObjectId(id);
       tipoHabitacion.setId(objectId);
        tipoHabitacionRepository.save(tipoHabitacion);
        
        return "redirect:/tiposHabitaciones";
    }
    
    
    
    @GetMapping("/tiposHabitaciones/{id}/delete")
    public String delete(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        tipoHabitacionRepository.deleteById(objectId);
        return "redirect:/tiposHabitaciones";
    }
}

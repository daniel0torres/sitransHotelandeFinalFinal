package co.edu.uniandes.hotelandes.controller;



import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.uniandes.hotelandes.model.Alojamiento;
import co.edu.uniandes.hotelandes.model.Habitacion;
import co.edu.uniandes.hotelandes.model.Reservas;
import co.edu.uniandes.hotelandes.model.Sede;
import co.edu.uniandes.hotelandes.repository.AlojamientoRepository;
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
        model.addAttribute("idSede", sedeId);
        return "habitaciones"; // Nombre del archivo de plantilla Thymeleaf
    }
    
    @GetMapping("/sedes/{sedeId}/habitaciones/new")
    public String createHabitacion(@PathVariable String sedeId, Model model) {
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("sedeId", sedeId); // Agrega el sedeId al modelo para usarlo en la vista
        return "habitacionesNew";
    }
  
    @PostMapping("/sedes/{sedeId}/habitaciones/new/save")
    public String habitacionCreateSave(@ModelAttribute Habitacion habitacion , @PathVariable String sedeId) {
        sedeRepository.insertHabitacion(sedeId, habitacion);
        return "redirect:/sedes/{sedeId}/habitaciones";
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

    @GetMapping("/sedes/{sedeId}/habitaciones/{habitacionNumero}/edit")
    public String update(@PathVariable String sedeId, @PathVariable String habitacionNumero, Model model) {
        ObjectId sedeIdObj = new ObjectId(sedeId);
        Sede sede = sedeRepository.findById(sedeIdObj).orElse(null);
        if (sede != null) {
            Habitacion habitacion = sedeRepository.findHabitacion(sede, habitacionNumero);
            if (habitacion!= null){
                model.addAttribute("habitacion", habitacion);
                return "habitacionesEdit";
            }
            return "redirect:/error";
        }
        else {
            return "redirect:/sedes";
        }
    }
    
    @PostMapping("/sedes/{sedeId}/habitaciones/{habitacionId}/edit/save")
    public String updateSave(@PathVariable String sedeId,@PathVariable String habitacionId, @ModelAttribute Habitacion habitacionActualizada, BindingResult result) {
        ObjectId sedeIdObj = new ObjectId(sedeId);
        Sede sede = sedeRepository.findById(sedeIdObj).orElse(null);
        if (sede != null) {
            List<Habitacion> habitaciones = sede.getHabitaciones();
            if (habitaciones != null) {
                for (int i =0 ; i < habitaciones.size(); i++) {
                    System.out.println("Comparando: " + habitacionId + " con " + habitaciones.get(i).getNumero());
                    if (habitacionId.equals(habitaciones.get(i).getNumero())) {
                        habitacionActualizada.setId(habitaciones.get(i).getId());
                        habitaciones.set(i, habitacionActualizada); 
                        break;
                    }
                }
                sedeRepository.save(sede); 
                return "redirect:/sedes/{sedeId}/habitaciones";
            
        }
    }
        return "error1";
        
    }

    @GetMapping("/sedes/{sedeId}/habitaciones/{numero}/delete")
    public String delete(@PathVariable String sedeId, @PathVariable String numero ) {
        ObjectId sedeIdOb = new ObjectId(sedeId);
        sedeRepository.deleteHabitacion(sedeIdOb, numero);
        return "redirect:/sedes/{sedeId}/habitaciones";
    }

    @Autowired
    AlojamientoRepository alojamientoRepository;


    @GetMapping("/sedes/{sedeId}/habitaciones/{habitacionId}/ocupacionAnual")
    public String ocupacionPorcentualAnual(@PathVariable("sedeId") String sedeId, @PathVariable("habitacionId") String habitacionId, Model model) {
        List<Alojamiento> alojamientos = alojamientoRepository.findAll();
        ObjectId habitacionObjectId = new ObjectId(habitacionId);
        int totalDiasOcupados = 0;
        LocalDate inicioAño = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate hoy = LocalDate.now();

        for (Alojamiento alojamiento : alojamientos) {
        
            if (habitacionObjectId.equals(alojamiento.getHabitacionId())) {

                LocalDate llegada = alojamiento.getFechaLlegada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate salida = alojamiento.getFechaSalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
                if ((llegada.isBefore(hoy) || llegada.isEqual(hoy)) &&
                    (salida.isAfter(inicioAño) || salida.isEqual(inicioAño))) {
                    llegada = llegada.isBefore(inicioAño) ? inicioAño : llegada;
                    salida = salida.isAfter(hoy) ? hoy : salida;
    
                    totalDiasOcupados += salida.toEpochDay() - llegada.toEpochDay();
                }
            }
        }
    
        int totalDiasHastaHoy = hoy.getDayOfYear();
        double ocupacion = (double) totalDiasOcupados / totalDiasHastaHoy * 100;
    
        model.addAttribute("ocupacion", ocupacion);
        System.out.println(totalDiasHastaHoy);
        System.out.println(totalDiasOcupados);
        return "ocupacion";
    }

}
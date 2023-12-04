
package co.edu.uniandes.hotelandes.controller;
import co.edu.uniandes.hotelandes.model.Alojamiento;
import co.edu.uniandes.hotelandes.model.Cliente;
import co.edu.uniandes.hotelandes.model.Consumo;
import co.edu.uniandes.hotelandes.model.Reserva;
//import co.edu.uniandes.hotelandes.model.Reservas;
import co.edu.uniandes.hotelandes.model.Sede;
import co.edu.uniandes.hotelandes.repository.AlojamientoRepository;
import co.edu.uniandes.hotelandes.repository.ClienteRepository;
import co.edu.uniandes.hotelandes.repository.ReservasRepository;
import co.edu.uniandes.hotelandes.repository.SedeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlojamientoController {


    @Autowired
    AlojamientoRepository alojamientoRepository;

    //@Autowired
    //ReservasRepository reservaRepository;
    
    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/alojamientos")
    public String obtenerAlojamientosConReserva(Model model) {
        //List<Alojamiento> alojamientos = alojamientoRepository.findAlojamientoConReserva();
        model.addAttribute("alojamientos", alojamientoRepository.findAll());
        return "alojamientos"; 
    }

    
    @GetMapping("/alojamientos/new/{id_cliente}")
    public String create(Model model, @PathVariable("id_cliente") String id_cliente) throws IllegalArgumentException {
        ObjectId objectId = new ObjectId(id_cliente);
        Cliente clienteActual = clienteRepository.findById(objectId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id_cliente));
        model.addAttribute("alojamiento", new Alojamiento());
        model.addAttribute("cliente", clienteActual);
        return "alojamientoNew";
    }
   
    



    @PostMapping("/alojamientos/new/save/{id_cliente}")
    public String createSave(@PathVariable("id_cliente") String id_cliente,@RequestParam String habitacion, @RequestParam("id_reserva") String id_reserva, Model model) {
                System.out.println("----------------------------------------------EMPEZANDO" );

        Cliente cliente = clienteRepository.findById(new ObjectId(id_cliente)).orElse(null);
        ArrayList<Reserva> reservas = cliente.getReservas();
        Reserva reservaOriginal = null;
        System.out.println("----------------------------------------------aun no entra" );
        for (Reserva reserva : reservas) {
            System.out.println("----------------------------------------------entro al ciclo" );
            ObjectId CompararReservaId =reserva.getId();
            
            if ( CompararReservaId.toHexString().equals(id_reserva)){
                reservaOriginal = reserva;
            }
        }    
        //Reservas reservaObj = reservaRepository.findById(reservaId).orElse(null);
        /* 
        if (reservaObj == null) {
            model.addAttribute("error", "Reserva no encontrada.");
            return "redirect:/alojamientos"; 
        }

        Alojamiento alojamientoExistente = alojamientoRepository.findByReserva(reservaId);
        if (alojamientoExistente != null) {
            model.addAttribute("error", "Ya existe un alojamiento con esta reserva.");
            return "redirect:/alojamientos"; 
        }
        */
        ObjectId reservaId =reservaOriginal.getId();
        
        String clienteName = id_cliente; 
        Date fechaLlegada =reservaOriginal.getCheckin();
        Date fechaSalida = reservaOriginal.getCheckout();
        ObjectId habitacionId = new ObjectId(habitacion);
        List<Sede> sedes = sedeRepository.findAll();
        String habitacionNombre = sedeRepository.findHabitacionById(habitacionId, sedes);
        Alojamiento alojamiento = new Alojamiento(habitacionId, reservaId, clienteName, fechaLlegada, fechaSalida, habitacionNombre);
        alojamientoRepository.insert(alojamiento);
        return "redirect:/alojamientos";
    }


    @GetMapping("/alojamientos/{id}/edit")
    public String update(@PathVariable String id, Model model) {
        ObjectId objectId = new ObjectId(id);
        Alojamiento alojamiento = alojamientoRepository.findById(objectId).orElse(null);
        if (alojamiento != null) {
            model.addAttribute("alojamiento", alojamiento);
            return "alojamientoEdit";
        }

        else {
            return "redirect:/alojamientos";
        }
    }
    
    @PostMapping("/alojamientos/{id}/edit/save")
    public String updateSave(@PathVariable("id") String id,  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaLlegada, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaSalida, @RequestParam String habitacion) {
        ObjectId objectId = new ObjectId(id);
        Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(objectId);
        Alojamiento alojamiento = alojamientoOptional.orElse(null);
        alojamiento.setHabitacion(habitacion);
        if (fechaLlegada != null) {
            alojamiento.setFechaLlegada(fechaLlegada);
        }
        if (fechaSalida != null){
            alojamiento.setFechaSalida(fechaSalida);
        }        
       
        alojamientoRepository.save(alojamiento);
        
        return "redirect:/alojamientos";
    }
    
    @GetMapping("/alojamientos/{id}/delete")
    public String delete(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        alojamientoRepository.deleteById(objectId);
        return "redirect:/alojamientos";
    }
}

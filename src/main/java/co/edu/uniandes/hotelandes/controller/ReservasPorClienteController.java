package co.edu.uniandes.hotelandes.controller;

import co.edu.uniandes.hotelandes.model.Cliente;
import co.edu.uniandes.hotelandes.model.Reserva;
import co.edu.uniandes.hotelandes.model.Sede;

import co.edu.uniandes.hotelandes.repository.ClienteRepository;
import co.edu.uniandes.hotelandes.repository.SedeRepository;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservasPorClienteController {

 

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    SedeRepository sedeRepository;

    private void fillModel(Model model, Cliente clienteActual) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("cliente", clienteActual);
    }

    private Cliente findClienteById(String id_cliente) throws IllegalArgumentException {
        ObjectId objectId = new ObjectId(id_cliente);
        Cliente clienteActual = clienteRepository.findById(objectId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id_cliente));
        return clienteActual;
    }

    @GetMapping("/clientes/{id_cliente}/reservas")
    public String read(Model model, @PathVariable("id_cliente") String id_cliente) {
        Cliente clienteActual = findClienteById(id_cliente);
        List<Reserva> reservas = clienteActual.getReservas();

        List<Sede> sedes = sedeRepository.findAll();
        LinkedHashMap<String, Sede> mapaSedes = new LinkedHashMap<>();
        sedes.forEach(sede -> mapaSedes.put(sede.getId().toHexString(), sede));
        LinkedList<ReservaConSede> reservaConSedes = new LinkedList<>();
        reservas.forEach(reserva -> {
            String nombreSede = "Sin sede";
            Sede sede = mapaSedes.get(reserva.getSede());
            if (sede != null) {
                nombreSede = sede.getNombre();
            }
            reservaConSedes.add(new ReservaConSede(reserva, nombreSede));
        });
        System.out.println(clienteActual);

        model.addAttribute("reservas", reservaConSedes);
        model.addAttribute("cliente", clienteActual);
        //model.addAttribute("mapaSedes", mapaSedes);
        return "reservasPorCliente";
    }

    class ReservaConSede extends Reserva {

        private String nombreSede;

        public ReservaConSede(Reserva reserva, String nombreSede) {
            this.setId(reserva.getId());
            this.setCheckin(reserva.getCheckin());
            this.setCheckout(reserva.getCheckout());
            this.setSede(reserva.getSede());
            this.nombreSede = nombreSede;

        }

        public String getNombreSede() {
            return nombreSede;
        }

        public void setNombreSede(String nombreSede) {
            this.nombreSede = nombreSede;
        }

    }

    @GetMapping("/clientes/{id_cliente}/reservas/new")
    public String create(Model model, @PathVariable("id_cliente") String id_cliente) throws IllegalArgumentException {
        Cliente clienteActual = findClienteById(id_cliente);
        List<Sede> sedes = sedeRepository.findAll();
        model.addAttribute("sedes", sedes);
        fillModel(model, clienteActual);

        return "reservaPorClienteNew";
    }

    @PostMapping("/clientes/{id_cliente}/reservas/new/save")
    public String createSave(@ModelAttribute Reserva reserva, @PathVariable("id_cliente") String id_cliente) {
        Cliente clienteActual = findClienteById(id_cliente);
        reserva.setIdCustom();
        clienteActual.getReservas().add(reserva);
        clienteRepository.save(clienteActual);

        return "redirect:/clientes/{id_cliente}/reservas";
    }

    @GetMapping("/clientes/{id_cliente}/reservas/{num_reserva}/edit")
    public String update(Model model, @PathVariable("id_cliente") String id_cliente, @PathVariable("num_reserva") int numReserva) {
        Cliente clienteActual = findClienteById(id_cliente);
        Reserva reserva = clienteActual.getReservas().get(numReserva);

        if (reserva != null) {
            fillModel(model, clienteActual);
            List<Sede> sedes = sedeRepository.findAll();
            model.addAttribute("sedes", sedes);
            model.addAttribute("num_reserva", numReserva);
            model.addAttribute("reserva", clienteActual.getReservas().get(numReserva));
            return "reservaPorClienteEdit";
        } else {
            return "redirect:/clientes/{id_cliente}/reservas";
        }

    }

    @PostMapping("/clientes/{id_cliente}/reservas/{num_reserva}/edit/save")
    public String updateSave(@ModelAttribute Reserva reserva, @PathVariable("id_cliente") String id_cliente, @PathVariable("num_reserva") int numReserva) {
        Cliente clienteActual = findClienteById(id_cliente);
        clienteActual.getReservas().set(numReserva, reserva);
        clienteRepository.save(clienteActual);

        return "redirect:/clientes/{id_cliente}/reservas";
    }

    @GetMapping("/clientes/{id_cliente}/reservas/{num_reserva}/delete")
    public String delete(@PathVariable("id_cliente") String id_cliente, @PathVariable("num_reserva") int numReserva) {
        Cliente clienteActual = findClienteById(id_cliente);
        clienteActual.getReservas().remove(numReserva);
        clienteRepository.save(clienteActual);

        return "redirect:/clientes/{id_cliente}/reservas";
    }

}

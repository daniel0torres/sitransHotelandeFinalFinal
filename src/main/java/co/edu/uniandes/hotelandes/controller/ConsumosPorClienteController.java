package co.edu.uniandes.hotelandes.controller;

import co.edu.uniandes.hotelandes.model.Cliente;
import co.edu.uniandes.hotelandes.model.Consumo;
import co.edu.uniandes.hotelandes.model.Hello;
import co.edu.uniandes.hotelandes.repository.ClienteRepository;
import jakarta.websocket.server.PathParam;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConsumosPorClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    private void fillModel(Model model, Cliente clienteActual) {
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("cliente", clienteActual);
    }

    private Cliente findClienteById(String id_cliente) throws IllegalArgumentException {
        ObjectId objectId = new ObjectId(id_cliente);
        Cliente clienteActual = clienteRepository.findById(objectId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id_cliente));
        return clienteActual;
    }

    @GetMapping("/clientes/{id_cliente}/consumos")
    public String read(Model model, @PathVariable("id_cliente") String id_cliente) {
        Cliente clienteActual = findClienteById(id_cliente);
        List<Consumo> consumos = clienteActual.getConsumos();
        System.out.println(clienteActual);
        model.addAttribute("consumos", consumos);
        model.addAttribute("cliente", clienteActual);

        return "consumosPorCliente";
    }

    @GetMapping("/clientes/{id_cliente}/consumos/new")
    public String create(Model model, @PathVariable("id_cliente") String id_cliente) throws IllegalArgumentException {
        Cliente clienteActual = findClienteById(id_cliente);
        fillModel(model, clienteActual);

        return "consumoPorClienteNew";
    }

    @PostMapping("/clientes/{id_cliente}/consumos/new/save")
    public String createSave(@ModelAttribute Consumo consumo, @PathVariable("id_cliente") String id_cliente) {
        Cliente clienteActual = findClienteById(id_cliente);
        clienteActual.getConsumos().add(consumo);
        clienteRepository.save(clienteActual);

        return "redirect:/clientes/{id_cliente}/consumos";
    }

    @GetMapping("/clientes/{id_cliente}/consumos/{num_consumo}/edit")
    public String update(Model model, @PathVariable("id_cliente") String id_cliente, @PathVariable("num_consumo") int numConsumo) {
        Cliente clienteActual = findClienteById(id_cliente);
        Consumo consumo = clienteActual.getConsumos().get(numConsumo);

        if (consumo != null) {
            fillModel(model, clienteActual);
            model.addAttribute("num_consumo", numConsumo);
            model.addAttribute("consumo",clienteActual.getConsumos().get(numConsumo));
            return "consumoPorClienteEdit";
        } else {
            return "redirect:/clientes/{id_cliente}/consumos";
        }

    }

    @PostMapping("/clientes/{id_cliente}/consumos/{num_consumo}/edit/save")
    public String updateSave(@ModelAttribute Consumo consumo, @PathVariable("id_cliente") String id_cliente, @PathVariable("num_consumo") int numConsumo) {
        Cliente clienteActual = findClienteById(id_cliente);
        clienteActual.getConsumos().set(numConsumo, consumo);
        clienteRepository.save(clienteActual);

        return "redirect:/clientes/{id_cliente}/consumos";
    }

    @GetMapping("/clientes/{id_cliente}/consumos/{num_consumo}/delete")
    public String deleteConsumo(@PathVariable("id_cliente") String id_cliente, @PathVariable("num_consumo") int numConsumo) {
        Cliente clienteActual = findClienteById(id_cliente);
        clienteActual.getConsumos().remove(numConsumo);
        clienteRepository.save(clienteActual);

        return "redirect:/clientes/{id_cliente}/consumos";
    }


    @GetMapping("/clientes/{id_cliente}/consumos/filtrar")
    public String filtrarConsumos(@PathVariable String id_cliente, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model model) { 
        Cliente cliente = clienteRepository.findById(id_cliente)
                         .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con ID: " + id_cliente));

        
        if (cliente != null) {
            List<Consumo> consumosFiltrados = cliente.getConsumos().stream()
                    .filter(consumo -> !consumo.getFecha().before(startDate) && !consumo.getFecha().after(endDate))
                    .collect(Collectors.toList());
            
            model.addAttribute("consumos", consumosFiltrados);
        } else {
            model.addAttribute("mensajeError", "Cliente no encontrado");
        }

        return "consumosFiltrados";
    }


}

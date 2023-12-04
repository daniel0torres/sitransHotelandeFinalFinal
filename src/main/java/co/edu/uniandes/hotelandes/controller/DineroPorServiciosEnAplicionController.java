package co.edu.uniandes.hotelandes.controller;

import co.edu.uniandes.hotelandes.model.Cliente;
import co.edu.uniandes.hotelandes.model.Consumo;
import co.edu.uniandes.hotelandes.model.ConsumoServicioHabitacion;
import co.edu.uniandes.hotelandes.model.Sede;
import co.edu.uniandes.hotelandes.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import co.edu.uniandes.hotelandes.repository.ClienteRepository;
import co.edu.uniandes.hotelandes.repository.SedeRepository;
import co.edu.uniandes.hotelandes.repository.ServicioRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DineroPorServiciosEnAplicionController {

    @Autowired
    SedeRepository sedeRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ServicioRepository servicioRepository;

    @GetMapping("/FrontCostoServicio")
    public String consumo(Model model) {

        return "FrontCostoServicio";
    }

    @PostMapping("/habitacion/consumos/ok")
    public String consultarConsumos(Model model, @RequestParam("nombreHabitacion") String nomHabForm) throws IllegalArgumentException {
        System.out.println("----------------------------------------------" + nomHabForm);
        List<Cliente> clientes = clienteRepository.findAll();
        LinkedHashMap<String, ServicioTotal> serviciosTotalMap = new LinkedHashMap<>();

        for (Cliente cliente : clientes) {
            ArrayList<Consumo> consumos = cliente.getConsumos();
            for (Consumo consumo : consumos) {
                System.out.println("---------<<<<< " + consumo);
                String nombreHabitacion = consumo.getNombreHabitacion();

                if (nombreHabitacion.equals(nomHabForm)) {
                    System.out.println("----ENTRO  -----<<<<< " + consumo);
                    //String nombreHabitacion = consumo.getNombre();
                    ObjectId idServicio = consumo.getServicio();
                    Servicio servicio = servicioRepository.findById(idServicio).orElse(null);
                    ServicioTotal csh = serviciosTotalMap.get(idServicio.toHexString());
                    if (csh == null) {
                        csh = new ServicioTotal(servicio);
                        serviciosTotalMap.put(idServicio.toHexString(), csh);
                    }
                    csh.addTotal(consumo.getCosto());

                }

            }

        }

        model.addAttribute("consumosTotal", serviciosTotalMap.values());

        return "TotalConsumoServioHabitacion";
    }

    class ServicioTotal {

        Servicio servicio;
        double total;

        public ServicioTotal(Servicio servicio) {
            this.servicio = servicio;
            total = 0;
        }

        public Servicio getServicio() {
            return servicio;
        }

        public void setServicio(Servicio servicio) {
            this.servicio = servicio;
        }

        public double getTotal() {
            return total;
        }

        public void addTotal(double add) {
            total += add;
        }

    }

}

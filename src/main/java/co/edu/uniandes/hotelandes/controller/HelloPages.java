package co.edu.uniandes.hotelandes.controller;

import co.edu.uniandes.hotelandes.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloPages {

    @Autowired
    HelloRepository helloRepository;

    @GetMapping(value = "/hello")
    public String helloPage() {
        return "hello";
    }
    
    @GetMapping("/hellos")
    public String Spas(Model model){
        model.addAttribute("hellos", helloRepository.findAll());
        return "hellos";
    }

}
//
//
//@Controller
//public class HelloController {
//
//    @Autowired
//    private HelloRepository helloRepository;
//    @Autowired
//    private SpaRepository spaRepository;
//
//    @GetMapping("/spas")
//    public String Spas(Model model){
//        model.addAttribute("spas", spaRepository.darSpas());
//        return "Spas";
//    }
//
//
//    @GetMapping("/spas/new")
//    public String spasForm(Model model){
//        model.addAttribute("spa", new Spa());
//        return "spaNuevo";
//    }
//
//    @PostMapping("/spas/new/save")
//    public String supermercadoGuardar(@ModelAttribute Servicio servicio,@ModelAttribute Spa spa) {
//        servicioRepository.save(servicio);  //<------- aqui salva e; servicio
//        spaRepository.insertSpa(servicio.getId(),spa.getDuracion(), spa.getTipo_servicio());
//        return "redirect:/spas";
//    }
//
//
//    @GetMapping("/spas/{id}/edit")
//    public String spaEditarForm(@PathVariable("id") int id, Model model){
//        Spa spa = spaRepository.darSpa(id);
//        if (spa!=null){
//            model.addAttribute("spa", spa);
//            return "spaEditar";
//
//        } else{
//            return "redirect:/spas";
//        }
//    }
//
//
//    @PostMapping("/spas/{id}/edit/save")
//    public String spaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Spa spa){
//        spaRepository.updateSpa(id, spa.getDuracion(), spa.getTipo_servicio());
//        return "redirect:/spas";
//
//    }
//
//
//    @GetMapping("/spas/{id}/delete")
//    public String spaEliminar(@PathVariable("id") int id){
//        spaRepository.deleteSpa(id);
//        return "redirect:/spas";
//    }
//
//
//    
//}

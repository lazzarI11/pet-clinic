package lazar.spring.petclinic.controllers;

import lazar.spring.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping("/vets")
    public String listVets(Model model){
        System.out.println(vetService.findAll().size());
        model.addAttribute("vets",vetService.findAll());
        return "/vets/index";
    }
    @RequestMapping("/*")
    public String notFound(){
        return "notimplemented";
    }

}

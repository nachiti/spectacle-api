package com.example.spectacle.controller;

import com.example.spectacle.model.Spectacle;
import com.example.spectacle.repository.SpectacleRepository;
import com.example.spectacle.service.CommentaireService;
import com.example.spectacle.service.SpectacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class backOfficeController {

    @Autowired
    SpectacleService spectacleService;

    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private SpectacleRepository spectacleRepository;

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "loginAdmin";
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listSpectacles",spectacleService.getAllSpectacles());
        return "index";
    }

    @GetMapping("/showNewSpectacleForm")
    public String showNewSpectacleForm(Model model){
        Spectacle spectacle = new Spectacle();
        model.addAttribute("spectacle",spectacle);
        return "new_spectacle";
    }

    //save spectacle to database
    @PostMapping("/saveSpectacle")
    public String saveSpectacle(@ModelAttribute("spectacle") Spectacle spectacle){
       //  spectacleService.addSpectacle(spectacle);
         return "redirect:/admin";
    }

    //get spectacles by id
    @GetMapping("spectacles/{id}")
    public Spectacle getSpectaclesById(@PathVariable Long id) {
        return spectacleService.getSpectaclesById(id);
    }


    //update spectacle
    @PutMapping("/spectacles/{id}")
    public Spectacle updateSpectacle(@RequestBody Spectacle newSpectacle,
                                     @PathVariable Long id){
        return spectacleService.updateSpectacle(newSpectacle, id);
    }
    //delete spectacle
    @DeleteMapping("/spectacles/{id}")
    public void deleteSpectacle(@PathVariable Long id){
        spectacleService.deleteSpectacle(id);
    }

    //delete commentaires
    @DeleteMapping("/commentaires/{id}")
    public void deleteCommentaire(@PathVariable Long id){
        commentaireService.deleteCommentaire(id);
    }

}

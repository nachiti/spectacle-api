package com.example.spectacle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur par defaut
 */
@Controller
public class SpectacleControllerDefault {

    /**
     * Page par defaut redirection vers la page liste
     * @return
     */
    @RequestMapping("/")
    public String home() {
        return "redirect:/admin/index";
    }

}

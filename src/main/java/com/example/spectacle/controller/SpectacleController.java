package com.example.spectacle.controller;


import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.service.CommentaireServiceInt;
import com.example.spectacle.service.SpectacleServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpectacleController {

    @Autowired
    private SpectacleServiceInt spectacleServiceInt;
    @Autowired
    private CommentaireServiceInt commentaireServiceInt;

    //get All Spectacles
    @GetMapping("/spectacles")
    public List<Spectacle> getAllSpectacles() {
        return spectacleServiceInt.getAllSpectacles();
    }

    //get spectacles by id
    @GetMapping("/spectacles/{id}")
    public Spectacle getSpectaclesById(@PathVariable Long id) {
        return spectacleServiceInt.getSpectaclesById(id);
    }

    //get spectacles by different criteria
    @GetMapping("/spectacles/seachBy")
    public List<Spectacle> getSpectaclesByCriteria(@RequestParam(name = "ville", required = false) String ville,
                                                   @RequestParam(name = "type", required = false)String type,
                                                   @RequestParam(name = "prixMin", required = false)Double prixMin,
                                                   @RequestParam(name = "prixMax", required = false) Double prixMax,
                                                   @RequestParam(name = "accesHandicap", required = false)Boolean accesHandicap) {
        return spectacleServiceInt.getSpectaclesByCriteria(ville, type, prixMin, prixMax, accesHandicap);
    }

    //add commentaire in spectacle
    @PostMapping("/Commentaires")
    public Commentaire addCommentaire(@RequestBody Commentaire commentaire){
        return commentaireServiceInt.addCommentaire(commentaire);
    }

}

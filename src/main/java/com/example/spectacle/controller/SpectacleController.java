package com.example.spectacle.controller;


import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.service.CommentaireServiceInt;
import com.example.spectacle.service.SpectacleServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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


    //**Api d' administation**//

    //add spectacle
    @Secured(value = "ROLEADMIN")
    @PostMapping("/spectacles")
    public Spectacle addSpectacle(@RequestBody Spectacle spectacle){
        return spectacleServiceInt.addSpectacle(spectacle);
    }

    //update spectacle
    @PutMapping("/spectacles/{id}")
    public Spectacle updateSpectacle(@RequestBody Spectacle newSpectacle,
                                     @PathVariable Long id){
       return spectacleServiceInt.updateSpectacle(newSpectacle, id);
    }
    //delete spectacle
    @DeleteMapping("/spectacles/{id}")
    public void deleteSpectacle(@PathVariable Long id){
        spectacleServiceInt.deleteSpectacle(id);
    }

    //delete spectacle
    @DeleteMapping("/commentaires/{id}")
    public void deleteCommentaire(@PathVariable Long id){
        commentaireServiceInt.deleteCommentaire(id);
    }
}

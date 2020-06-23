package com.example.spectacle.controller;


import com.example.spectacle.exception.ResourceNotFoundException;
import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.repository.CommentaireRepository;
import com.example.spectacle.repository.SpectacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpectacleController {

    @Autowired
    private SpectacleRepository spectacleRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;

    //get Spectacles
    @GetMapping("spectacles")
    public List<Spectacle> getAllSpectacles(){
        return this.spectacleRepository.findAll();
    }

    //get spectacles by different criteria
    @GetMapping("/spectacles/{q}")
    public List<Spectacle> getSpectaclesByCriteria(@PathVariable(name = "q") String q) throws ResourceNotFoundException {
        return spectacleRepository.findAll();
    }

    //add commentaire in spectacle
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire){
        return this.commentaireRepository.save(commentaire);
    }

}

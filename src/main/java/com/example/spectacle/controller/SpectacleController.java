package com.example.spectacle.controller;


import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.service.CommentaireServiceInt;
import com.example.spectacle.service.SpectacleServiceInt;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class SpectacleController {

    @Autowired
    private SpectacleServiceInt spectacleServiceInt;
    @Autowired
    private CommentaireServiceInt commentaireServiceInt;

    //get All Spectacles
    @GetMapping("/api/spectacles")
    public List<Spectacle> getAllSpectacles() {
        return spectacleServiceInt.getAllSpectacles();
    }

    //get spectacles by id
    @GetMapping("/api/spectacles/{id}")
    public Spectacle getSpectaclesById(@PathVariable Long id) {
        return spectacleServiceInt.getSpectaclesById(id);
    }

    //get spectacles by different criteria
    @GetMapping("/api/spectacles/seachBy")
    public List<Spectacle> getSpectaclesByCriteria(@RequestParam(name = "ville", required = false) String ville,
                                                   @RequestParam(name = "type", required = false)String type,
                                                   @RequestParam(name = "prixMin", required = false)Double prixMin,
                                                   @RequestParam(name = "prixMax", required = false) Double prixMax,
                                                   @RequestParam(name = "accesHandicap", required = false)Boolean accesHandicap) {
        return spectacleServiceInt.getSpectaclesByCriteria(ville, type, prixMin, prixMax, accesHandicap);
    }

    //add commentaire in spectacle
    @PostMapping("/api/Commentaires")
    public Commentaire addCommentaire(@RequestBody Commentaire commentaire){
        return commentaireServiceInt.addCommentaire(commentaire);
    }

    //get list imagesName of spectacle
    @GetMapping(value = "/api/spectacles/{id}/images")
    public List<String> getImagesnameOfSpectacle(@PathVariable Long id){
        return new ArrayList<>(spectacleServiceInt.getSpectaclesById(id).getPhotosUrl());
    }

    // get images of spectacle
    @GetMapping(value = "/api/images/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImagesOfSpectacle(@PathVariable String name) throws IOException {
        ClassLoader  classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("static/images/" + name);
        assert inputStream != null;
        return IOUtils.toByteArray(inputStream);
    }


    //**Api d' administation**//

    //add spectacle
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

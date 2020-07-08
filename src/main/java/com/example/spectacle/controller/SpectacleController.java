package com.example.spectacle.controller;


import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.model.Test;
import com.example.spectacle.repository.TestRepo;
import com.example.spectacle.service.CommentaireServiceInt;
import com.example.spectacle.service.SpectacleServiceInt;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpectacleController {

    @Autowired
    private SpectacleServiceInt spectacleServiceInt;
    @Autowired
    private CommentaireServiceInt commentaireServiceInt;

    @Autowired
    TestRepo testRepo;

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

    //get all commentaires of spactacle
    @GetMapping("/api/spectacles/{idSpectacle}/commentaires")
    public List<Commentaire> getAllCommentairesOfSpectacle(@PathVariable Long idSpectacle){
        return commentaireServiceInt.getAllCommentairesOfSpectacle(idSpectacle);
    }


    //add commentaire in spectacle
    @PostMapping("/api/spectacles/{idSpectacle}/commentaires")
    public Commentaire addCommentaire(@PathVariable(value = "idSpectacle") Long idSpectacle, @RequestBody Commentaire commentaire){
        Spectacle spectacle = spectacleServiceInt.getSpectaclesById(idSpectacle);
        commentaire.setSpectacle(spectacle);
        return commentaireServiceInt.addCommentaire(commentaire);
    }


   /* //add test
    @PostMapping("/api/spectacles/test")
    public Test addTest(@RequestBody Test test) {
        return testRepo.save(test);
    }*/

    //add test
    @PostMapping(path = "/api/spectacles/{id}/test", consumes = "application/json", produces = "application/json" )
    public Test addTest(@PathVariable(value = "id") Long id, @RequestBody Test test) {
        test.setCoucou(id);
        return testRepo.save(test);
    }

    //add commentaire in spectacle
    @GetMapping("/api/test")
    public List<Test> getTest(){
        return testRepo.findAll();
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

    //delete commentaires
    @DeleteMapping("/commentaires/{id}")
    public void deleteCommentaire(@PathVariable Long id){
        commentaireServiceInt.deleteCommentaire(id);
    }

}

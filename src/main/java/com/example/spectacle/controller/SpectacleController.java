package com.example.spectacle.controller;


import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.model.Test;
import com.example.spectacle.repository.TestRepo;
import com.example.spectacle.service.CommentaireService;
import com.example.spectacle.service.SpectacleService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class SpectacleController {

    @Autowired
    private SpectacleService spectacleService;
    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    TestRepo testRepo;



    //get All Spectacles
    @GetMapping("/spectacles")
    @ResponseBody
    public List<Spectacle> getAllSpectacles() {
        return spectacleService.getAllSpectacles();
    }

    //get spectacles by id
    @GetMapping("spectacles/{id}")
    @ResponseBody
    public Spectacle getSpectaclesById(@PathVariable Long id) {
        return spectacleService.getSpectaclesById(id);
    }

    //get spectacles by different criteria
    @GetMapping("spectacles/searchBy")
    @ResponseBody
    public List<Spectacle> getSpectaclesByCriteria(@RequestParam(name = "ville", required = false) String ville,
                                                   @RequestParam(name = "type", required = false)String type,
                                                   @RequestParam(name = "prixMin", required = false)Double prixMin,
                                                   @RequestParam(name = "prixMax", required = false) Double prixMax,
                                                   @RequestParam(name = "accesHandicap", required = false)Boolean accesHandicap) {
        return spectacleService.getSpectaclesByCriteria(ville, type, prixMin, prixMax, accesHandicap);
    }

    //get all commentaires of spactacle
    @GetMapping("/spectacles/{idSpectacle}/commentaires")
    @ResponseBody
    public List<Commentaire> getAllCommentairesOfSpectacle(@PathVariable Long idSpectacle){
        return commentaireService.getAllCommentairesOfSpectacle(idSpectacle);
    }


    //add commentaire in spectacle
    @PostMapping("/spectacles/{idSpectacle}/commentaires")
    @ResponseBody
    public Commentaire addCommentaire(@PathVariable(value = "idSpectacle") Long idSpectacle, @RequestBody Commentaire commentaire){
        Spectacle spectacle = spectacleService.getSpectaclesById(idSpectacle);
        commentaire.setSpectacle(spectacle);
        return commentaireService.addCommentaire(commentaire);
    }


   /* //add test
    @PostMapping("/api/spectacles/test")
    public Test addTest(@RequestBody Test test) {
        return testRepo.save(test);
    }*/

/*    //add test
    @PostMapping(path = "/api/spectacles/{id}/test", consumes = "application/json", produces = "application/json" )
    public Test addTest(@PathVariable(value = "id") Long id, @RequestBody Test test) {
        test.setCoucou(id);
        return testRepo.save(test);
    }*/

    //add commentaire in spectacle
    @GetMapping("/test")
    public List<Test> getTest(){
        return testRepo.findAll();
    }


    //get list imagesName of spectacle
    @GetMapping(value = "/spectacles/{id}/images")
    public List<String> getImagesnameOfSpectacle(@PathVariable Long id){
        return new ArrayList<>(spectacleService.getSpectaclesById(id).getPhotosUrl());
    }

    // get images of spectacle
/*    @GetMapping(value = "/images/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImagesOfSpectacle(@PathVariable String name) throws IOException {
        ClassLoader  classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("static/images/" + name);
        assert inputStream != null;
        return IOUtils.toByteArray(inputStream);
    }*/

    // get images of spectacle
    @GetMapping(value = "/images/{name}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("name") String name){
        if (name != null && !name.isEmpty()) {
            try {
                Path path = Paths.get("uploads",name);
                byte[] buffer = Files.readAllBytes(path);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }

}

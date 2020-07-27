package com.example.spectacle.controller;


import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.model.Utilisateur;
import com.example.spectacle.repository.UtilisateurRepository;
import com.example.spectacle.service.CommentaireService;
import com.example.spectacle.service.SpectacleService;
import com.example.spectacle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur de l'api accessible depuis l'application mobile
 */
@RestController
@RequestMapping("/api")
public class SpectacleControllerApi {

    @Autowired
    private SpectacleService spectacleService;
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private UserService userService;

    /**
     * Récupérer toutes les spectacles
     * @return List<Spectacle>
     */
    @GetMapping("/public/spectacles")
    public List<Spectacle> getAllSpectacles() {
        return spectacleService.getAllSpectacles();
    }

    /**
     * Récupérer un spectacle par id
     * @param id id spectacle
     * @return Spectacle
     */
    @GetMapping("/public/spectacles/{id}")
    public Spectacle getSpectaclesById(@PathVariable Long id) {
        return spectacleService.getSpectaclesById(id);
    }

    /**
     * Chercher les spectacles par different critère
     * @param ville
     * @param type
     * @param prixMin
     * @param prixMax
     * @param accesHandicap
     * @return
     */
    @GetMapping("/public/spectacles/searchBy")
    public List<Spectacle> getSpectaclesByCriteria(@RequestParam(name = "ville", required = false) String ville,
                                                   @RequestParam(name = "type", required = false)String type,
                                                   @RequestParam(name = "prixMin", required = false)Double prixMin,
                                                   @RequestParam(name = "prixMax", required = false) Double prixMax,
                                                   @RequestParam(name = "accesHandicap", required = false)Boolean accesHandicap) {
        return spectacleService.getSpectaclesByCriteria(ville, type, prixMin, prixMax, accesHandicap);
    }

    /**
     * Récupérer la liste des commentaires d'un spectacle
     * @param idSpectacle
     * @return
     */
    @GetMapping("/public/spectacles/{idSpectacle}/commentaires")
    public List<Commentaire> getAllCommentairesOfSpectacle(@PathVariable Long idSpectacle){
        return commentaireService.getAllCommentairesOfSpectacle(idSpectacle);
    }

    /**
     * Ajouter un commentaire à un spectacle
      * @param idSpectacle
     * @param commentaire
     * @return
     */
    @PostMapping("/user/spectacles/{idSpectacle}/commentaires")
    public Commentaire addCommentaire(@PathVariable(value = "idSpectacle") Long idSpectacle,
                                      @RequestBody Commentaire commentaire){
        Spectacle spectacle = spectacleService.getSpectaclesById(idSpectacle);
        commentaire.setSpectacle(spectacle);
        return commentaireService.addCommentaire(commentaire);
    }

    /**
     * Récupérer la liste des images d'un spectacle
     * @param id id spectacle
     * @return
     */
    @GetMapping(value = "/public/spectacles/{id}/images")
    public List<String> getImagesnameOfSpectacle(@PathVariable Long id){
        return new ArrayList<>(spectacleService.getSpectaclesById(id).getPhotosUrl());
    }

    /**
     * Récupérer une image par nom
     * @param name nom de l'image
     * @return
     */
    @GetMapping(value = "/public/images/{name}")
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

    /**
     * Ajouter un nouveau utilisateur
     * @param utilisateur
     * @return
     */
    @PostMapping(value = "/public/createUser")
    public Utilisateur addNewUtilisateur(@RequestBody Utilisateur utilisateur){
        return userService.addUtilisateur(utilisateur);
    }

    /**
     *  Modifier un utilisateur
     * @param utilisateur
     * @param username
     * @return
     */
    @PostMapping(value = "/user/update/{username}")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur,
                                         @PathVariable(value = "username") String username){
        return userService.updateUtilisateur(utilisateur,username);
    }

    /**
     * Supprimer un utilisateur
     * @param username
     */
    @DeleteMapping(value = "/user/delete/{username}")
    public void deleteUtilisateur( @PathVariable(value = "username") String username){
         userService.deleteUtilisateur(username);
    }

    /**
     * Recuperer un utilisateur
     * @param username
     * @return
     */
    @GetMapping(value = "/user/{username}")
    public Utilisateur getUtilisateur( @PathVariable(value = "username") String username){
        return userService.findUtilisateurByUsername(username);
    }

    /**
     * Ajouter un spectacle au favoris d'un utilisateur
     * @param username
     * @param id id spectacle
     * @return
     */
    @PostMapping(value = "/user/{username}/addSpectacleFavoris/{id}")
    public Utilisateur addSpectacleToFavoris( @PathVariable(value = "username") String username,
                                              @PathVariable(value = "id") long id){
        return userService.addSpectacleToFavoris(username,id);
    }

    /**
     * Supprimer un spectacle du favoris d'un utilisateur
     * @param username
     * @param id id spectacle
     * @return
     */
    @PostMapping(value = "/user/{username}/deleteSpectacleFavoris/{id}")
    public Utilisateur deleteSpectacleFromFavoris( @PathVariable(value = "username") String username,
                                              @PathVariable(value = "id") long id){
        return userService.deleteSpectacleFromFavoris(username,id);
    }

}

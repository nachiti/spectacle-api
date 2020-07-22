package com.example.spectacle.controller;

import com.example.spectacle.model.Spectacle;
import com.example.spectacle.repository.SpectacleRepository;
import com.example.spectacle.service.CommentaireService;
import com.example.spectacle.service.SpectacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class BackOfficeController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @Autowired
    SpectacleService spectacleService;
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private SpectacleRepository spectacleRepository;

    /**
     * Login form
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Logout form
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    /**
     *  Afficher la liste des spectacles sur la page d'acueil
     * @param model
     * @return
     */
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listSpectacles", spectacleService.getAllSpectacles());
        return "index";
    }



    /**
     * afficher la vue detail spectacle
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/showDetailSpectacle/{id}")
    public String viewDetailSpectacle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("spectacle", spectacleService.getSpectaclesById(id));
        return "detail_spectacle";
    }

    /**
     * Afficher le formulaire d'ajout d'un spectacle
     * @param model
     * @return
     */
    @GetMapping("/showNewSpectacleForm")
    public String showNewSpectacleForm(Model model) {
        Spectacle spectacle = new Spectacle();
        model.addAttribute("spectacle", spectacle);
        return "new_spectacle";
    }

    /**
     * save spectacle to database
     * @param spectacle
     * @param files
     * @return
     */
    @PostMapping("/saveSpectacle")
    public String saveSpectacle(@ModelAttribute("spectacle") Spectacle spectacle,
                                @RequestParam("files") MultipartFile[] files) {
        //ajouter les images
        if (files != null && !files[0].isEmpty()) {
            Collection<String> newImageNames = addImagesInSpectacle(
                    new ArrayList<>(), spectacle.getTitre(), files);
            spectacle.setPhotosUrl(newImageNames);
        }
        spectacleService.addSpectacle(spectacle);
        return "redirect:/admin/showDetailSpectacle/"+spectacle.getId();
    }

    /**
     *  Update spectacle to database
     * @param spectacle
     * @param files
     * @param removeImages
     * @return
     */
    @PostMapping("/updateSpectacle")
    public String updateSpectacle(@ModelAttribute("spectacle") Spectacle spectacle,
                                  @RequestParam("files") MultipartFile[] files,
                                  @RequestParam(name = "removeImages", required = false) String removeImages) {
        Collection<String> spectacleImageList = getSpectacleImageList(spectacle.getId());
        spectacle.setPhotosUrl(spectacleImageList);
        //supprimer les images à supprimer
        if (removeImages != null && !removeImages.isEmpty()) {
            Collection<String> imageNames = removeImagesFromSpectacle(spectacle.getPhotosUrl(), removeImages);
            spectacle.setPhotosUrl(imageNames);
        }
        //ajouter les images s'il le faut
        if (files != null && !files[0].isEmpty()) {
            Collection<String> newImageNames = addImagesInSpectacle(
                    spectacle.getPhotosUrl(), spectacle.getTitre(), files);
            spectacle.setPhotosUrl(newImageNames);
        }
        spectacleService.addSpectacle(spectacle);
        return "redirect:/admin/showDetailSpectacle/"+spectacle.getId();
    }

    /**
     * Recuperer la liste des images d'un spectacle
     * @param id
     * @return
     */
    private Collection<String> getSpectacleImageList(long id) {
        Spectacle oldSpectacle = spectacleService.getSpectaclesById(id);
        if (oldSpectacle != null && oldSpectacle.getPhotosUrl()!= null) {
            return oldSpectacle.getPhotosUrl();
        }else {
            return new ArrayList<>();
        }
    }

    /**
     * Ajout des nouveaux images à un spectacle
     * @param newImageNames
     * @param titre
     * @param files
     * @return
     */
    private Collection<String> addImagesInSpectacle( Collection<String> newImageNames, String titre,
                                                     MultipartFile[] files) {
        int cpt = newImageNames.size() + 1;
        for (MultipartFile file : files) {
            //renommé les images en format titre_1.png
            String titreSpectacle = titre.replaceAll("[^a-zA-Z0-9]", "");
            String fileName = titreSpectacle + "_" + cpt + "." + file.getOriginalFilename().split("\\.")[1];
            Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
            try {
                Files.write(fileNameAndPath, file.getBytes());
                newImageNames.add(fileName);
                cpt++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newImageNames;
    }

    /**
     * supprimer les images d'un spectacle
     * @param imageNames
     * @param removeImages
     * @return
     */
    private Collection<String> removeImagesFromSpectacle(Collection<String> imageNames, String removeImages) {
        String[] tabRemoveImages = removeImages.split(",");
        for (int i = 0; i < tabRemoveImages.length; i++) {
            Path path = Paths.get("uploads", tabRemoveImages[i]);
            File image = new File(String.valueOf(path));
            if (image.delete()) {
                imageNames.remove(tabRemoveImages[i]);
            } else {
                throw new RuntimeException("Failed to delete the image " + tabRemoveImages[i]);
            }
        }
        return imageNames;
    }

    /**
     * Afficher le formulaire de modification d'un spectacle
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Spectacle spectacle = spectacleService.getSpectaclesById(id);
        model.addAttribute("spectacle", spectacle);
        return "update_spectacle";
    }

    /**
     *  Supprimer un spectacle
     * @param id
     * @return
     */
    @GetMapping("/deleteSpectacle/{id}")
    public String deleteSpectacle(@PathVariable(value = "id") Long id) {
        spectacleService.deleteSpectacle(id);
        return "redirect:/admin/";
    }

    /**
     *  Recuperer un image du spectacle
     * @param name
     * @return
     */
    @GetMapping(value = "/images/{name}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("name") String name) {
        if (name != null && !name.isEmpty()) {
            try {
                Path path = Paths.get("uploads", name);
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

    //*******************************************************************************************************************//
    //get spectacles by id
    @GetMapping("spectacles/{id}")
    public Spectacle getSpectaclesById(@PathVariable Long id) {
        return spectacleService.getSpectaclesById(id);
    }


    //update spectacle
    @PutMapping("/spectacles/{id}")
    public Spectacle updateSpectacle(@RequestBody Spectacle newSpectacle,
                                     @PathVariable Long id) {
        return spectacleService.updateSpectacle(newSpectacle, id);
    }

    //delete commentaires
    @DeleteMapping("/commentaires/{id}")
    public void deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
    }

}

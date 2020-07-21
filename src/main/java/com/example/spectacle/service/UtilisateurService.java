package com.example.spectacle.service;

import com.example.spectacle.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {

     void initUtilisateur();

     Utilisateur addUtlisisateur(Utilisateur utilisateur);
     Utilisateur findUserByUsername(String username);

    List<Utilisateur> getAllSpectacleFavorisOfUtilisateur(Long idUser);
    Utilisateur addSpectacleToFavoris(Long idUser, Long idSpectacle);
    void deleteSpectacleFromFavoris(Long idUser, Long idSpectacle);
}

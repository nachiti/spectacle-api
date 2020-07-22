package com.example.spectacle.service;

import com.example.spectacle.model.Admin;
import com.example.spectacle.model.UserDetail;
import com.example.spectacle.model.Utilisateur;

import java.util.List;

public interface UserService {

    /**
     * recuper username, password et role de l'utilisateur et de l'Admin
     * @param username
     * @return
     */
    UserDetail findUserAndAdminByUsername(String username);

    //********** Admin service **********//

    void initAdmin();

    Admin addAdmin(Admin admin);

    Admin findAdminByUsername(String username);

    //********** Utilisateur service **********//

    void initUtilisateur();

    Utilisateur addUtlisisateur(Utilisateur utilisateur);

    Utilisateur findUserByUsername(String username);

    List<Utilisateur> getAllSpectacleFavorisOfUtilisateur(Long idUser);

    Utilisateur addSpectacleToFavoris(Long idUser, Long idSpectacle);

    void deleteSpectacleFromFavoris(Long idUser, Long idSpectacle);
}

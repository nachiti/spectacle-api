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

    Utilisateur addUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Utilisateur utilisateur, String username);

    void deleteUtilisateur(String username);

    Utilisateur findUtilisateurByUsername(String username);

    Utilisateur addSpectacleToFavoris(String username, Long idSpectacle);

    Utilisateur deleteSpectacleFromFavoris(String username, Long idSpectacle);
}

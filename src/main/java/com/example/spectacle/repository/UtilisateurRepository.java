package com.example.spectacle.repository;

import com.example.spectacle.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
     Utilisateur findByUsername(String username);
}

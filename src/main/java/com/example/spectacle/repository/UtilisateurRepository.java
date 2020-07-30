package com.example.spectacle.repository;

import com.example.spectacle.model.UserDetail;
import com.example.spectacle.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

     Utilisateur findByUsername(String username);

     void deleteByUsername(String username);
}

package com.example.spectacle.repository;

import com.example.spectacle.model.UserDetail;
import com.example.spectacle.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

     @Query(value = "SELECT * FROM admin UNION SELECT * FROM utilisateur", nativeQuery = true)
     UserDetail findUserAndAdminByUsername(String username);

     Utilisateur findByUsername(String username);
}

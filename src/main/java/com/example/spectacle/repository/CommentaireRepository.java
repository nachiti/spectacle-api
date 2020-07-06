package com.example.spectacle.repository;

import com.example.spectacle.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

    @Query("SELECT c FROM Commentaire c WHERE c.idSpectacle = :id ORDER BY c.date DESC")
    List<Commentaire> getAllCommentairesOfSpectacle(@Param("id")Long idSpectacle);
}

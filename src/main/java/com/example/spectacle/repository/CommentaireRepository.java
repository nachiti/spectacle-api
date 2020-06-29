package com.example.spectacle.repository;

import com.example.spectacle.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

}

package com.example.spectacle.service;

import com.example.spectacle.model.Commentaire;

import java.text.ParseException;
import java.util.List;

public interface CommentaireServiceInt {
    void initCommentaire() throws ParseException;

    List<Commentaire> getAllCommentairesOfSpectacle(Long idUser);

    Commentaire addCommentaire(Commentaire commentaire);

    void deleteCommentaire(Long id);
}

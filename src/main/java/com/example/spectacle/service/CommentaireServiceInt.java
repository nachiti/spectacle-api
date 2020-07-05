package com.example.spectacle.service;

import com.example.spectacle.model.Commentaire;

import java.text.ParseException;

public interface CommentaireServiceInt {
    void initCommentaire() throws ParseException;

    Commentaire addCommentaire(Commentaire commentaire);

    void deleteCommentaire(Long id);
}

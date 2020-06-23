package com.example.spectacle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "commentaires")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pseudonyme;
    private double note;
    @Lob
    private String texte;
    private String date;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Spectacle spectacle;

    public Commentaire() {
    }

    public Commentaire(String pseudonyme, double note, String texte, String date, Spectacle spectacle) {
        this.pseudonyme = pseudonyme;
        this.note = note;
        this.texte = texte;
        this.date = date;
        this.spectacle = spectacle;
    }

    public long getId() {
        return id;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


package com.example.spectacle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Commentaire implements Serializable {

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

    public Commentaire(String pseudonyme, double note, String texte, String date, Spectacle spectacle) {
        this.pseudonyme = pseudonyme;
        this.note = note;
        this.texte = texte;
        this.date = date;
        this.spectacle = spectacle;
    }
}


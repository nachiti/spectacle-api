package com.example.spectacle.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Spectacle spectacle;
    @Column(name="spectacle_id", updatable=false,insertable=false)
    private Long idSpectacle;

    public Commentaire(String pseudonyme, double note, String texte, Date date, Spectacle spectacle) {
        this.pseudonyme = pseudonyme;
        this.note = note;
        this.texte = texte;
        this.date = date;
        this.spectacle = spectacle;
    }

    public Commentaire(String pseudonyme, double note, String texte, Date date) {
        this.pseudonyme = pseudonyme;
        this.note = note;
        this.texte = texte;
        this.date = date;
    }
}


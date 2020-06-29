package com.example.spectacle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Spectacle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titre;
    @ElementCollection
    private Collection<String> photosUrl;
    @Enumerated(EnumType.STRING)
    private TypeSpectacle typeSpectacle;
    private double latitude, longitude;
    private String adresse;
    private Date dateHeure;
    private double prix;
    @Lob
    private String description;
    private boolean accesHadicap;
    @Enumerated(EnumType.STRING)
    private InterExter interExter;
    private boolean favoris;
    @OneToMany(mappedBy = "spectacle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Commentaire> commentaires;

    public Spectacle(String titre, TypeSpectacle typeSpectacle, double latitude, double longitude, String adresse, Date dateHeure, double prix, String description, boolean accesHadicap, InterExter interExter) {
        this.titre = titre;
        this.typeSpectacle = typeSpectacle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adresse = adresse;
        this.dateHeure = dateHeure;
        this.prix = prix;
        this.description = description;
        this.accesHadicap = accesHadicap;
        this.interExter = interExter;
        this.favoris = false;
    }
}
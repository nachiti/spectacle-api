package com.example.spectacle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "spectacles")
public class Spectacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titre;
    private Collection<String> photosUrl;
    @Enumerated(EnumType.STRING)
    private TypeSpectacle typeSpectacle;
    private double latitude, longitude;
    private String adresse;
    private String dateHeure;
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

    public Spectacle() {
    }

    public Spectacle(String titre, TypeSpectacle typeSpectacle, double latitude, double longitude, String adresse, String dateHeure, double prix, String description, boolean accesHadicap, InterExter interExter) {
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
        this.favoris = true;
    }

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Collection<String> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(Collection<String> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public TypeSpectacle getTypeSpectacle() {
        return typeSpectacle;
    }

    public void setTypeSpectacle(TypeSpectacle typeSpectacle) {
        this.typeSpectacle = typeSpectacle;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(String dateHeure) {
        this.dateHeure = dateHeure;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccesHadicap() {
        return accesHadicap;
    }

    public void setAccesHadicap(boolean accesHadicap) {
        this.accesHadicap = accesHadicap;
    }

    public InterExter getInterExter() {
        return interExter;
    }

    public void setInterExter(InterExter interExter) {
        this.interExter = interExter;
    }

    public boolean isFavoris() {
        return favoris;
    }

    public void setFavoris(boolean favoris) {
        this.favoris = favoris;
    }

    public Collection<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}

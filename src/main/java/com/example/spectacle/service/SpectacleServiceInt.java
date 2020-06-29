package com.example.spectacle.service;

import com.example.spectacle.model.Spectacle;

import java.text.ParseException;
import java.util.List;

public interface SpectacleServiceInt {
    void initSpectacle() throws ParseException;
    List<Spectacle> getAllSpectacles();
    Spectacle getSpectaclesById(long id);
    List<Spectacle> getSpectaclesByCriteria(String ville,
                                            String type,
                                            Double prixMin,
                                            Double prixMax,
                                            Boolean accesHandicap);

    Spectacle addSpectacle(Spectacle spectacle);

    Spectacle updateSpectacle(Spectacle newSpectacle, Long id);

    void deleteSpectacle(Long id);
}

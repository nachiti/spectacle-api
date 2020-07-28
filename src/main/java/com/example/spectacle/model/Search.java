package com.example.spectacle.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Search {
    private String ville;
    private List<TypeSpectacle> typeSpectacleList;
    private int prixMax;
    private int prixMin;
    private List<InterExter> interExterList;
    private List<Boolean> AccesHandicaplist;

}

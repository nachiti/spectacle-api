package com.example.spectacle.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Search implements Serializable {
    private String ville;
    @ElementCollection
    private List<TypeSpectacle> typeSpectacleList;
    private int prixMax;
    private int prixMin;
    @ElementCollection
    private List<InterExter> interExterList;
    @ElementCollection
    private List<Boolean> accesHandicapList;

}

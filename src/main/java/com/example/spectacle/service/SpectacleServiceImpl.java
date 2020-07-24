package com.example.spectacle.service;

import com.example.spectacle.exception.SpectacleNotFoundException;
import com.example.spectacle.model.InterExter;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.model.TypeSpectacle;
import com.example.spectacle.repository.CommentaireRepository;
import com.example.spectacle.repository.SpectacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class SpectacleServiceImpl implements SpectacleService {

    @Autowired
    private SpectacleRepository spectacleRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;

    private static final String DATE_PATTERN = "dd/MM/yyyy hh:mm";

    @Override
    public void initSpectacle() throws ParseException {

        Spectacle spectacle1 = new Spectacle(
                "AMIR"
                , new ArrayList<String>(Arrays.asList("photo1_1.jpg","photo1_2.jpg"))
                , TypeSpectacle.Musique
                , 50.632538
                , 3.0781436
                , "Zenith de lille, Lille Grand Palais, 1 Boulevard des Cités Unies, 59000 Lille, France"
                , new SimpleDateFormat(DATE_PATTERN).parse("12/07/2020 20:30"),45.99
                , "Amir, l’artiste aux plus de 600 000 albums vendus et aux multiples tubes et récompenses est enfin de retour !\n" +
                        "\n" +
                        "Avec la sortie de ‘La Fête’, le premier single de son prochain album, le message est clair, universel et fédérateur : Fêtons la vie tous ensemble et tout le temps que ça dure. Prolongez la fête en retrouvant Amir sur scène en 2021, notamment au Zénith de Paris, le 6 mars 2021 !\n" +
                        "\n" +
                        "Réservez vos places de concert pour : AMIR - ZENITH ARENA LILLE\n" +
                        "Le prix des places est à partir de : 35.00 €\n" +
                        "Date : mardi 2 mars 2021\n" +
                        "Vous disposez par ailleurs du service e-ticket pour imprimer vos billets à domicile dès la fin de commande pour AMIR ainsi que du plan de salle interactif pour choisir vos places dans le lieu : ZENITH ARENA LILLE."
                ,true
                , InterExter.Interieur );

        spectacleRepository.save(spectacle1);

        Spectacle spectacle2 = new Spectacle(
                "VITAA & SLIMANE"
                , new ArrayList<String>(Arrays.asList("photo2_1.jpg"))
                , TypeSpectacle.Musique
                ,50.61420440673828
                ,3.1350412368774414
                , "stade pierre mauroy, 261 Boulevard de Tournai, Villeneuve-d'Ascq, France"
                , new SimpleDateFormat(DATE_PATTERN).parse("27/03/2021 20:30"),37.50
                , "VersuS, le titre de la première chanson issu d’un projet incroyable et évident.\n" +
                "\n" +
                "VersuS, les confessions musicales de Vitaa et Slimane, deux artistes que le destin a décidé de réunir le temps d’un album duo. Disponible depuis le 23 aout dernier, il a été certifié disque de platine en 48 jours seulement avec plus de 100 000 exemplaires vendus.\n" +
                "\n" +
                "VersuS Tour, une tournée des zéniths à deux. Un spectacle musical unique mettant en scène les interprètes des tubes « Je te le donne » et « Ça va ça vient ».\n" +
                "\n" +
                "Alors que le Zénith Arena de Lille le 3 avril 2020 affiche déjà complet, Vitaa et Slimane vous donnent rendez-vous pour une date exceptionnelle au Stade Pierre Mauroy de Lille samedi 27 mars 2021 ! »"
                ,true
                , InterExter.Interieur );

        spectacleRepository.save(spectacle2);

        Spectacle spectacle3 = new Spectacle(
                "LE PLUS GRAND CABARET DU MONDE"
                , new ArrayList<String>(Arrays.asList("photo3_1.jpg","photo3_2.jpg"))
                , TypeSpectacle.Danse
                , 50.632538
                , 3.0781436
                , "Zenith de lille, Lille Grand Palais, 1 Boulevard des Cités Unies, 59000 Lille, France"
                , new SimpleDateFormat(DATE_PATTERN).parse("02/12/2020 20:30"),39.00
                , "« LE PLUS GRAND CABARET DU MONDE »\n" +
                "\n" +
                "Présenté et animé par\n" +
                "\n" +
                "Patrick SÉBASTIEN\n" +
                "et plus de 50 Artistes sur scène.\n" +
                "\n" +
                "Pendant plus de vingt ans, “Le plus grand cabaret du monde” a enchanté toutes les générations du plus petit au plus grand. Les artistes du monde entier, magiciens, acrobates, clowns, voltigeurs, vous ont émerveillé à la télévision. Aujourd’hui ils traversent l’écran pour venir chez vous. Un spectacle féérique de deux heures présenté par Patrick Sébastien arrive dans vos villes. Le talent, le frisson, le mystère, le rire, la beauté, l’exceptionnel, feront éclater un feu d’artifice de performances uniques. « Le plus grand cabaret du monde » en tournée, c’est « le rêve en vrai ».\n" +
                "\n" +
                "Réservez vos places de spectacle et comedie musicale pour : LE PLUS GRAND CABARET DU MONDE - ZENITH ARENA LILLE\n" +
                "Le prix des places est à partir de : 39.00 €\n" +
                "Date : mercredi 2 décembre 2020\n" +
                "Vous disposez par ailleurs du service e-ticket pour imprimer vos billets à domicile dès la fin de commande pour LE PLUS GRAND CABARET DU MONDE ainsi que du plan de salle interactif pour choisir vos places dans le lieu : ZENITH ARENA LILLE.\n" +
                "\n"
                ,true
                , InterExter.Interieur );

        spectacleRepository.save(spectacle3);

        Spectacle spectacle4 = new Spectacle(
                "JEREMY FERRARI Anesthésie Générale"
                , new ArrayList<String>(Arrays.asList("photo4_1.jpg","photo4_2.jpg"))
                , TypeSpectacle.Humour
                ,50.629191
                , 3.058120
                , "Théâtre Sebastopol, Place Sébastopol, 59000 Lille, France"
                , new SimpleDateFormat(DATE_PATTERN).parse("07/09/2020 20:00"),39.00
                , "Le nouveau spectacle de Jérémy Ferrari\n" +
                "\n" +
                "Après la religion et la guerre, Jérémy Ferrari s’attaque à la santé !\n" +
                "\n" +
                "Une nouvelle thématique explosive pour l’humoriste au succès retentissant : 300 000 spectateurs en salle, DVD de platine, spectacle de l’année et record de ventes en 2017, tournée à guichets fermés en France, en Suisse, en Belgique, au Québec et aux USA !\n" +
                "\n" +
                "Le phénomène de l’humour revient avec le one man show le plus attendu de 2020."
                ,true
                , InterExter.Interieur );

        spectacleRepository.save(spectacle4);
    }

    @Override
    public List<Spectacle> getAllSpectacles() {
        return spectacleRepository.findAll();
    }

    @Override
    public Spectacle getSpectaclesById(long id) {
        return spectacleRepository.findById(id)
                .orElseThrow(() -> new SpectacleNotFoundException(id));
    }

    @Override
    public List<Spectacle> getSpectaclesByCriteria(String ville, String type, Double prixMin, Double prixMax, Boolean accesHandicap) {

        return spectacleRepository.findAll((Specification<Spectacle>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();

            if(!StringUtils.isEmpty(ville) ){
                p = cb.and(p,cb.like(cb.lower(root.get("adresse")),"%"+ville.toLowerCase()+"%"));
            }
            if(!StringUtils.isEmpty(type) ){
                p = cb.and(p,cb.like(root.get("typeSpectacle"),"%"+type+"%"));
            }
            if(!StringUtils.isEmpty(accesHandicap) ){
                p = cb.and(p,cb.equal(root.get("accesHadicap"),accesHandicap));
            }
            if (Objects.nonNull(prixMin) && Objects.nonNull(prixMax) && prixMin<=prixMin){
                p = cb.and(p,cb.between(root.get("prix"),prixMin,prixMax));
            }else if(Objects.nonNull(prixMin)) {
                p = cb.and(p,cb.greaterThanOrEqualTo(root.get("prix"),prixMin));
            }else if(Objects.nonNull(prixMax)) {
                p = cb.and(p,cb.lessThanOrEqualTo(root.get("prix"),prixMax));
        }
            return p;
        });
    }

    @Override
    public Spectacle addSpectacle(Spectacle spectacle) {
        return spectacleRepository.save(spectacle);
    }

    @Override
    public Spectacle updateSpectacle(Spectacle newSpectacle, Long id) {
        return spectacleRepository.findById(id)
                .map(spectacle -> {
                    spectacle.setTitre(newSpectacle.getTitre());
                    spectacle.setTypeSpectacle(newSpectacle.getTypeSpectacle());
                    spectacle.setLatitude(newSpectacle.getLatitude());
                    spectacle.setLongitude(newSpectacle.getLongitude());
                    spectacle.setAdresse(newSpectacle.getAdresse());
                    spectacle.setDateHeure(newSpectacle.getDateHeure());
                    spectacle.setPrix(newSpectacle.getPrix());
                    spectacle.setDescription(newSpectacle.getDescription());
                    spectacle.setAccesHadicap(newSpectacle.isAccesHadicap());
                    spectacle.setPhotosUrl(newSpectacle.getPhotosUrl());
                    return spectacleRepository.save(spectacle);
                })
                .orElseGet(() -> {
                    newSpectacle.setId(id);
                    return spectacleRepository.save(newSpectacle);
                });
    }

    @Override
    public void deleteSpectacle(Long id) {
        spectacleRepository.deleteById(id);
    }


}

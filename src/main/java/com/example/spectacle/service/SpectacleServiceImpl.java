package com.example.spectacle.service;

import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.InterExter;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.model.TypeSpectacle;
import com.example.spectacle.repository.CommentaireRepository;
import com.example.spectacle.repository.SpectacleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class SpectacleServiceImpl implements SpectacleServiceInt {

    @Autowired
    private SpectacleRepository spectacleRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;


    @Override
    public void initSpectacle() {
        Spectacle spectacle1 = new Spectacle(
                "AMIR"
                , TypeSpectacle.Musique
                , 50.632538
                , 3.0781436
                , "Zenith de lille, Lille Grand Palais, 1 Boulevard des Cités Unies, 59000 Lille, France"
                , "12/07/2020 20:30",45.99
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
                , TypeSpectacle.Musique
                ,50.61420440673828
                ,3.1350412368774414
                , "stade pierre mauroy, 261 Boulevard de Tournai, Villeneuve-d'Ascq, France"
                , "27/03/2021 20:30",37.50
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
                , TypeSpectacle.Danse
                , 50.632538
                , 3.0781436
                , "Zenith de lille, Lille Grand Palais, 1 Boulevard des Cités Unies, 59000 Lille, France"
                , "02/12/2020 20:30",39.00
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
                , TypeSpectacle.Humour
                ,50.629191
                , 3.058120
                , "Théâtre Sebastopol, Place Sébastopol, 59000 Lille, France"
                , "07/09/2020 20:00",39.00
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
    public void initCommentaire() {

        List<Spectacle> spectacles = spectacleRepository.findAll();

        Commentaire commentaire1 = new Commentaire(
                "Patric"
                , 5.0,"Que ce soit dans le parterre ou les gradins, l'acoustique de cette salle est parfaite. On peut manger rapidement dans le hall et l'ouverture au public suffisamment avant le spectacle permet d'en profiter avant de s'installer. Les chaises positionnées dans le parterre sont confortables. J'ai passé une excellente soirée"
                , "23/06/2020 01:17"
                , spectacles.get(0));

        commentaireRepository.save(commentaire1);

        Commentaire commentaire2 = new Commentaire(
                "Thomas"
                , 5.0
                , "Bon accueil, nous avons été guidé afin de trouver notre place. Le son est très bon. Les tarifs en boisson restent correct. Par contre les écrans de retransmission sur les côtés de la scène devraient être un peu plus grand."
                , "23/06/2020 03:12"
                , spectacles.get(0));

        commentaireRepository.save(commentaire2);

        Commentaire commentaire3 = new Commentaire(
                "Alice"
                , 4.1
                , "Bonne organisation et gestion de la foule. Personnel très aimable et fort agréable qui nous guide avec un grand sourire à nos place. Personnel de la buvette très accueillant. Buvette chère néanmoins (2€ la bouteille d'eau de 0,5)."
                , "23/06/2020 14:10"
                , spectacles.get(0));

        commentaireRepository.save(commentaire3);

        Commentaire commentaire4 = new Commentaire(
                "Bob"
                , 3.5
                ,  "Prenez patience si vous stationnez votre auto au parking. Le moyen de vous rendre au Zenith est catastrophique... indications peu claires, prenez une marge pour vous rendre au spectacle ! Fauteuils ? Non, des chaises peu ergonomiques et inconfortables au possible."
                , "23/06/2020 01:45"
                , spectacles.get(0));

        commentaireRepository.save(commentaire4);

        Commentaire commentaire5 = new Commentaire(
                "Noémie"
                , 2.0
                ,"Superbe infrastructure mais vent froid sur nous pendant tout le spectacle... Nous avons du garder nos vestes tellement il faisait froid. Le parking à disposition est ridicule par rapport au nombre de places du théâtre et peu importe le temps dehors, l'ouverture des portes ne se fera qu'1h avant l'événement (même si le personnel est présent) Heureusement l'artiste valait le détour et il a rendu ce moment merveilleux."
                , "23/06/2020 04:30"
                , spectacles.get(0));

        commentaireRepository.save(commentaire5);

/*        for (int i = 0; i < spectacles.size(); i++) {

        }*/
    }
}

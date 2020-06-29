package com.example.spectacle.service;

import com.example.spectacle.model.Commentaire;
import com.example.spectacle.model.Spectacle;
import com.example.spectacle.repository.CommentaireRepository;
import com.example.spectacle.repository.SpectacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentaireServiceImpl implements CommentaireServiceInt {

    @Autowired
    private SpectacleRepository spectacleRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;

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
    }

    @Override
    public Commentaire addCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public void deleteCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }
}

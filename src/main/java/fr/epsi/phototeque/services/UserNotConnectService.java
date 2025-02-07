package fr.epsi.phototeque.services;

import fr.epsi.phototeque.models.Image;
import fr.epsi.phototeque.models.User;
import fr.epsi.phototeque.repositories.CategorieRepository;
import fr.epsi.phototeque.repositories.ImageRepository;
import fr.epsi.phototeque.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNotConnectService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private ImageRepository imageRepository;

    public Page<Image> getLastImage() {return imageRepository.findAllByOrderByDateCreationDesc(PageRequest.of(0, 10));}

    public String getInfoImage(int idImage) {
        Optional<Image> imageOptional = imageRepository.findById(idImage);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return "Le nom de l'image : " + image.getNom() +
                    "\nLa catégorie de l'image : " + image.getCategorie().getNom() +
                    "\nLa description de l'image : " + image.getDescription() +
                    "\nLa taille de l'image : " + image.getTaille() +
                    "\nLa date de création de l'image : " + image.getDateCreation() +
                    "\nLa date de la dernière modif de l'image : " + image.getDateModif() +
                    "\nLa liste des éléments détectés de l'image : " + image.getListeElements() +
                    "\nPrésence d'individu dans l'image : " + image.isIndividu() +
                    "\nLes urls : " + image.getUrl();
        }
        return "L'image n'existe pas";
    }

}

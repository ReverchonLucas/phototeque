package fr.epsi.phototeque.services;

import fr.epsi.phototeque.models.ChangeImageRequest;
import fr.epsi.phototeque.models.AllUserRequest;
import fr.epsi.phototeque.models.Image;
import fr.epsi.phototeque.models.User;
import fr.epsi.phototeque.models.Categorie;
import fr.epsi.phototeque.repositories.ImageRepository;
import fr.epsi.phototeque.repositories.UserRepository;
import fr.epsi.phototeque.repositories.CategorieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class UserConnectService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategorieRepository categorieRepository;


    public String deleteImage(String token, Integer idImage) {
        String requestingUsername = jwtService.validateToken(token);
        if (requestingUsername == null) {
            return "Token invalide.";
        }
        User requestingUser = userRepository.findByPseudo(requestingUsername);
        if (requestingUser == null) {
            return "User invalide.";
        }
        Image image = imageRepository.findById(idImage).get();
        if (image.getUser().equals(requestingUser)) {
            imageRepository.delete(image);
            return "Image supp.";
        }
        else {
            return "Image n'est pas à l'utilisateur.";
        }
    }

    public String changeImage(ChangeImageRequest imageRequest){
        User resUser = testUser(imageRequest.getToken());
        if (resUser != null) {
            Optional<Image> optionalImage = imageRepository.findById(imageRequest.getIdImage());
            if (!optionalImage.isPresent()) {
                return "Image n'existe pas.";
            }
            Image image = optionalImage.get();
            if (image.getUser().equals(resUser)) {
                image.setNom(imageRequest.getNom());
                image.setDescription(imageRequest.getDescription());

                Categorie categorie = categorieRepository.findByNom(imageRequest.getCategorie());
                if (categorie != null) {
                    image.setCategorie(categorie);
                }
                else{
                    Categorie c = new Categorie();
                    c.setNom(imageRequest.getCategorie());
                    categorieRepository.save(c);
                    image.setCategorie(c);
                }
                image.setDateModif(new Date());
                imageRepository.save(image);
                return "Image changé.";
            } else { return "Image n'est pas à l'utilisateur.";}
        } else { return "Problème de token";}
    }

    public Page<Image> allImageDownload(AllUserRequest allUserRequest, int page, int size) throws IllegalAccessException{
        try {
            User resUser = testUser(allUserRequest.getToken());
            if (resUser != null) {
                Pageable pageable = PageRequest.of(page, size);
                return imageRepository.findByUserId(resUser.getId(), pageable);
            } else {
                return Page.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs : " + e.getMessage(), e);
        }
    }

    private User testUser(String token){
        try {
            String requestingUsername = jwtService.validateToken(token);
            if (requestingUsername == null) {
                return null;
            }

            User requestingUser = userRepository.findByPseudo(requestingUsername);
            if (requestingUser == null) {
                return null;
            }

            return requestingUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

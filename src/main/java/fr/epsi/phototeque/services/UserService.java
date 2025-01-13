package fr.epsi.phototeque.services;
import fr.epsi.phototeque.models.User;
import fr.epsi.phototeque.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public String addUser(User user) {
        if (user.getPassword().length() < 6 || !user.getPassword().matches(".*[!@#$%^&*()].*")) {
            return "Le mot de passe doit contenir au moins 6 caractères, dont au moins un caractère spécial.";
        } if (userRepository.findByPseudo(user.getPseudo()) != null) {
            return "Le pseudo est déjà utilisé.";
        }
        try {
            userRepository.save(user);
            return "Utilisateur ajouté avec succès.";
        } catch (Exception e) {
            return "Erreur lors de la création de l'utilisateur : " + e.getMessage();
        }
    }

    public String blockUser(String token, String targetPseudo) {
        if (this.verifAdmin(token).equals("ok")) {
            User targetUser = userRepository.findByPseudo(targetPseudo);
            if (targetUser == null) {
                return "Utilisateur cible introuvable.";
            }
            targetUser.setBlock(true);
            userRepository.save(targetUser);
            return "Utilisateur " + targetPseudo + " bloqué avec succès.";
        } else { return "Problème de token. Soit l'utilisateur n'a pas les droits de faire " +
                "cette action, soit il existe pas";}
    }

    public Page<User> allUser(String token, int page, int size) throws IllegalAccessException {
        String adminVerification = this.verifAdmin(token);
        if (!adminVerification.equals("ok")) {
            throw new IllegalAccessException("Problème avec le token de l'utilisateur connecté.");
        }
        try {
            Pageable pageable = PageRequest.of(page, size);
            return userRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs : " + e.getMessage(), e);
        }
    }

    public String verifAdmin(String token) {
        String requestingUsername = jwtService.validateToken(token);
        if (requestingUsername == null) {
            return "Token invalide.";
        }
        User requestingUser = userRepository.findByPseudo(requestingUsername);
        if (requestingUser == null) {
            return "Utilisateur non trouvé.";
        }
        if (!requestingUser.isAdmin()) {
            return "L'Utilisateur n'a pas les droits de faire cette action.";
        }
        return "ok";
    }

    public User findByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo);
    }
}


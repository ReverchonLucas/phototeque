package fr.epsi.phototeque.controllers;

import fr.epsi.phototeque.models.AuthRequest;
import fr.epsi.phototeque.models.User;
import fr.epsi.phototeque.services.JwtService;
import fr.epsi.phototeque.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        User user = userService.findByPseudo(authRequest.getPseudo());
        if (user == null || !user.getPassword().equals(authRequest.getPassword())) {
            return "Pseudo ou mot de passe incorrect.";
        }
        if (user.isBlocked()) {
            return "L'utilisateur est bloqué.";
        }
        return jwtService.generateToken(authRequest.getPseudo());
    }
}

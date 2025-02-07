package fr.epsi.phototeque.controllers;

import fr.epsi.phototeque.models.User;
import fr.epsi.phototeque.models.UserRequest;
import fr.epsi.phototeque.models.BlockUserRequest;
import fr.epsi.phototeque.models.AllUserRequest;
import fr.epsi.phototeque.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setPseudo(userRequest.getPseudo());
        user.setNom(userRequest.getNom());
        user.setPrenom(userRequest.getPrenom());
        user.setPassword(userRequest.getPassword());

        user.setAdmin(false);
        user.setBlocked(false);

        return userService.addUser(user);
    }

    @PutMapping("/block")
    public String blockUser(@RequestBody BlockUserRequest blockUserRequest) {
        return userService.blockUser(blockUserRequest.getToken(), blockUserRequest.getTargetUsername());
    }

    @GetMapping("/all")
    public Page<User> allUser(
            @RequestBody AllUserRequest allUserRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws IllegalAccessException {
        return userService.allUser(allUserRequest.getToken(), page, size);
    }
}
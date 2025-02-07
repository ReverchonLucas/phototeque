package fr.epsi.phototeque.controllers;

import fr.epsi.phototeque.models.Image;
import fr.epsi.phototeque.services.UserNotConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserNotConnectController {

    @Autowired
    private UserNotConnectService userNotConnectService;

    @GetMapping("/10image")
    public Page<Image> getLastImage() {
        return userNotConnectService.getLastImage();
    }

    @GetMapping("/info/image")
    public String getInfoImage(@RequestParam("idImage") int idImage) {
        return userNotConnectService.getInfoImage(idImage);
    }
}

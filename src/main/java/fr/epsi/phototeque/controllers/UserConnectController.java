package fr.epsi.phototeque.controllers;


import fr.epsi.phototeque.models.*;
import fr.epsi.phototeque.services.UserConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/users/connect")
public class UserConnectController {

    @Autowired
    private UserConnectService userConnectService;

    @DeleteMapping("/delete/image")
    public String addUser(@RequestBody DeleteImageRequest deleteImageRequest) {
       return userConnectService.deleteImage(deleteImageRequest.getToken(), deleteImageRequest.getIdImage());
    }

    @PutMapping("/change/image")
    public String updateImage(@RequestBody ChangeImageRequest changeImageRequest) {
        return userConnectService.changeImage(changeImageRequest);
    }

    @GetMapping("/all/image")
    public Page<Image> allImage(@RequestBody AllUserRequest allUserRequest,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size
    ) throws IllegalAccessException {
        return userConnectService.allImageDownload(allUserRequest,page,size);
    }
}

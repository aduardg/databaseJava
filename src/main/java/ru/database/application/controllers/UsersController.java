package ru.database.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.database.application.model.User;
import ru.database.application.service.UserService;

import java.awt.*;

@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/oauth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getAutorization(User user){
        User conterUser = userService.getAutorization(user);
        if(conterUser ==null){
            return ResponseEntity.badRequest().body(null);
        }
        else{
            return ResponseEntity.ok(conterUser);
        }
    }
}

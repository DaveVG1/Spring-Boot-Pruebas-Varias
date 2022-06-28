package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    private static final Pattern DNI_VALIDATOR = Pattern.compile("[0-9]{8}[A-Z]");

    @GetMapping("/dni/{id}/info")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        if(DNI_VALIDATOR.matcher(id).matches()){
            return ResponseEntity.ok(userService.getUserById(id));
        }
        return ResponseEntity.badRequest().body("DNI no valido");
    }

}

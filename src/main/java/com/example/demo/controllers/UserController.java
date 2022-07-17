package com.example.demo.controllers;

import com.example.demo.services.UserService;
import com.example.demo.validation.Dni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.regex.Pattern;


@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/dni/{id}/info")
    public ResponseEntity<?> getUserById(@PathVariable  @Dni String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }


}

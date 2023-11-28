package com.kevinraupp.studyws.resources;

import com.kevinraupp.studyws.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<User> findAll(){
        User user = new User("Teste","email@teste.com","321321321","password");
        return ResponseEntity.ok(user);
    }
}

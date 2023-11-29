package com.kevinraupp.studyws.resources;

import com.kevinraupp.studyws.entities.User;
import com.kevinraupp.studyws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.finall();
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "/{id}")
    public Optional<User> findById(@PathVariable Long id){
        return userService.findById(id);
    }

}

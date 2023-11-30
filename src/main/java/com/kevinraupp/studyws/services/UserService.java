package com.kevinraupp.studyws.services;

import com.kevinraupp.studyws.entities.User;
import com.kevinraupp.studyws.repositories.UserRepository;
import com.kevinraupp.studyws.resources.exceptions.DataBaseException;
import com.kevinraupp.studyws.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> finall(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public  User insert(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        try {
            if(!userRepository.existsById(id)) throw new ResourceNotFoundException(id);
            userRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }


    public User update(Long id,User user){
        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity,user);
            return userRepository.save(entity);
        }catch (EntityNotFoundException e ){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }


}

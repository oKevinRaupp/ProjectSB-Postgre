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
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public List<User> finall() {
        logger.info("Finding all users!");
        return userRepository.findAll();
    }

    public User findById(Long id) {
        logger.info("Finding one user!");
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user) {
        logger.info("creating a new user!");
        return userRepository.save(user);
    }


    public User update(Long id, User user) {
        try {
            logger.info("Updating user = " + id);
            User entity = userRepository.findById(id).orElseThrow();
            updateData(entity, user);
            return userRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }

    public void delete(Long id) {
        try {
            if (!userRepository.existsById(id)) throw new ResourceNotFoundException(id);
            logger.info("Deleting a user = " + id);
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


}

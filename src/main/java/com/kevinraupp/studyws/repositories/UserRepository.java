package com.kevinraupp.studyws.repositories;

import com.kevinraupp.studyws.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

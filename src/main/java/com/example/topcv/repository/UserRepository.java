package com.example.topcv.repository;

import com.example.topcv.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByIdAndStateNot(Integer integer, String stateDeleted);

  Optional<User> findByEmailAndStateNot(String email, String stateDeleted);
}

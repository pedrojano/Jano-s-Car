package com.janoscars.janoscars.repository;

import com.janoscars.janoscars.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    // Métodos para encontrar usuários por nome de usuário ou email

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    // Verifica se o usuário existe pelo nome de usuário ou email

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}

package com.janoscars.janoscars.service;

import com.janoscars.janoscars.model.User;
import com.janoscars.janoscars.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um componente de serviço Spring
public class UserService {

    private final UserRepository userRepository;

    // Injeção de dependência: O Spring automaticamente fornece uma instância de
    // UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Lógica de negócio para verificar se username ou email já estão em uso
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username '" + user.getUsername() + "' already exists.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email '" + user.getEmail() + "' already exists.");
        }
        // IMPORTANTE: Futuramente, a senha DEVE ser codificada (hash) aqui antes de
        // salvar!
        return userRepository.save(user);
    }

    // NOVO: Método para obter um usuário pelo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // NOVO: Método para obter todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // Atualiza apenas os campos permitidos
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        // A senha não deve ser atualizada diretamente assim; requer um método
        // específico e seguro

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
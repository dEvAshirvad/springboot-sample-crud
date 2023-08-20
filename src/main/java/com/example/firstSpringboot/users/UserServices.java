package com.example.firstSpringboot.users;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<Users>  getUsers() {
        return userRepository.findAll();
    }


    public Users addNew(Users users) {
        Optional<Users> userOptional = userRepository
                .findUserByEmail(users.getEmail());
        if (userOptional.isPresent())
            throw new IllegalStateException("user email already taken..");

        Users res = userRepository.save(users);
        return res;
    }

    public void deleteUserById(UUID id) {
        boolean exists = userRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("User not found...");
        userRepository.deleteById(id);
    }

    @Transactional
    public Users updateUser(UUID id, String name, String email) {
        Users users = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("user not found..."));

        if (name != null && !name.isEmpty() && !Objects.equals(users.getName(), name)){
            users.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(users.getEmail(), email)){
            Optional<Users> usersOptional = userRepository.findUserByEmail(email);

            if (usersOptional.isPresent()) {
                throw new IllegalStateException("email taken...");
            }

            users.setEmail(email);
        }

        return users;
    }
}

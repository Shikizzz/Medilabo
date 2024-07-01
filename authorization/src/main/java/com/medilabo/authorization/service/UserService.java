package com.medilabo.authorization.service;

import com.medilabo.authorization.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.medilabo.authorization.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean existsInDB(String username){
        return userRepository.existsById(username);
    }

    public User getUser(String username) {
        if (existsInDB(username)) {
            Optional<User> optionalUser = userRepository.findById(username);
            return optionalUser.get();
        } else return null;
    }

    public boolean addUser(User user){
        boolean newUser = !existsInDB(user.getUsername());
        if(newUser){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return newUser;
    }
    public boolean editUser(User user){
        boolean userAlreadyInDB = existsInDB(user.getUsername());
        if(userAlreadyInDB){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return userAlreadyInDB;
    }
    public boolean deleteUser(String username){
        if (existsInDB(username)) {
            userRepository.deleteById(username);
        }
        return existsInDB(username);
    }

}

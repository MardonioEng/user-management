package com.user.magement.service;

import com.user.magement.dto.UserDTO;
import com.user.magement.entity.User;
import com.user.magement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        logger.info("Iniciando getAllUsers");
        var userList = userRepository.findAll();
        return userList.stream()
                .map(user -> new UserDTO(user.getId(), user.getName()))
                .toList();
    }

    public UserDTO getUserById(Long id) {
        logger.info("Iniciando getUserById");
        var userEntity = this.userRepository.getReferenceById(id);
        return new UserDTO(userEntity.getId(), userEntity.getName());
    }

    public UserDTO createUser(UserDTO userDTO) {
        var newUser = new User();
        newUser.setName(userDTO.getName());
        var userEntity = this.userRepository.save(newUser);
        return new UserDTO(userEntity.getId(), userEntity.getName());
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        var entity = this.userRepository.getReferenceById(id);
        entity.setName(userDTO.getName());
        var savedEntity = this.userRepository.save(entity);
        return new UserDTO(savedEntity.getId(), savedEntity.getName());
    }

    public Void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
        return null;
    }

}

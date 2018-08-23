package com.sds.act.coe.user.service;

import com.sds.act.coe.user.repository.entity.UserEntity;
import com.sds.act.coe.user.message.Sender;
import com.sds.act.coe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;
    private Sender sender;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired Sender sender) {
        this.userRepository = userRepository;
        this.sender = sender;
    }

    public UserEntity register(UserEntity userEntity) {
        Optional<UserEntity> existingCustomer = userRepository.findByName(userEntity.getName());
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("is already exists");
        } else {
            userRepository.save(userEntity);
            sender.send(userEntity.getEmail());
        }
        return userEntity;
    }

    public List<UserEntity> search() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> get(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
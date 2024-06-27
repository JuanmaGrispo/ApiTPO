package com.apiTPO.technologyHouse.app.services;

import com.apiTPO.technologyHouse.app.dtos.UserIdDTO;
import com.apiTPO.technologyHouse.app.repositories.ShoppingCartRepository;
import com.apiTPO.technologyHouse.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Transactional
    public Boolean delete(UserIdDTO userIdDTO) {
        Long userId = userIdDTO.getUserId();
        if (userRepository.existsById(userId)) {
            cartRepository.deleteByUserId(userId);
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}


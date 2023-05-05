package com.example.webnovel.transaction.service;

import com.example.webnovel.user.persistence.User;
import com.example.webnovel.user.persistence.UserRepository;
import com.example.webnovel.transaction.dto.UserPointChargeResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPointService {
    private final UserRepository userRepository;

    public UserPointService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserPointChargeResponse chargePoints(Long userId, Long points) {
        // get user
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (!userOptional.isPresent()) {
            throw new ObjectNotFoundException(userId, User.class.getName());
        }

        User user = userOptional.get();
        user.chargePoint(points);
        userRepository.save(user);
        return new UserPointChargeResponse(user.getPoint());
    }
}

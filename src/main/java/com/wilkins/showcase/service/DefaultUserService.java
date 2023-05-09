package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.User;
import org.springframework.stereotype.Component;

@Component
public record DefaultUserService(UserRepository userRepository) implements UserService {
    @Override
    public User create(String name) {
        return userRepository.save(User.with(name));
    }
}

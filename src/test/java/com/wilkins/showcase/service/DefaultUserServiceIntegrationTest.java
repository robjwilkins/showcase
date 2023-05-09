package com.wilkins.showcase.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.wilkins.showcase.domain.User.userComparator;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class DefaultUserServiceIntegrationTest {

    @Autowired
    UserService underTest;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    void canCreateUser() {
        var bobFish = underTest.create("bob fish");

        assertThat(bobFish.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(bobFish.getId()).isNotEqualTo(0);

        assertThat(userRepository.findAll()).hasSize(1);
        assertThat(userRepository.findById(bobFish.getId()))
                .usingValueComparator(userComparator())
                .hasValue(bobFish);
    }
}

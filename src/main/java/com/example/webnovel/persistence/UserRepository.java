package com.example.webnovel.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<User> findUserById(Long userId);
}

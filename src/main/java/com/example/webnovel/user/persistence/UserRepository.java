package com.example.webnovel.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<User> findUserById(Long userId);
}

package ru.kochenkov.tym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kochenkov.tym.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    // см доку по spring JPA
    User findByUsername(String username);
}
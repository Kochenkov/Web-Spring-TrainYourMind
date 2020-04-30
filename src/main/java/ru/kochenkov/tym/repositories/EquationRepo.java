package ru.kochenkov.tym.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kochenkov.tym.models.Equation;
import ru.kochenkov.tym.models.User;

import java.util.List;

public interface EquationRepo extends CrudRepository<Equation, Integer> {

    /*
    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */
    Equation findById(Long id);
    List<Equation> findByUser(User user);
}
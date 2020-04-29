package ru.kochenkov.tym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kochenkov.tym.models.Equation;

public interface EquationRepo extends JpaRepository<Equation, Long> {

    Equation findEquationById(Long id);
}
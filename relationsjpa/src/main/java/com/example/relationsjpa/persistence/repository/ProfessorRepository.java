package com.example.relationsjpa.persistence.repository;

import com.example.relationsjpa.persistence.entity.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
package com.example.relationsjpa.persistence.repository;

import com.example.relationsjpa.persistence.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}

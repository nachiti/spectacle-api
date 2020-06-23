package com.example.spectacle.repository;

import com.example.spectacle.model.Spectacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectacleRepository extends JpaRepository<Spectacle,Long> {

}

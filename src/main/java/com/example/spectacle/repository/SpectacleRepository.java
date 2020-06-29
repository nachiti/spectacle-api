package com.example.spectacle.repository;

import com.example.spectacle.model.Spectacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpectacleRepository extends JpaRepository<Spectacle,Long>, JpaSpecificationExecutor<Spectacle> {

}

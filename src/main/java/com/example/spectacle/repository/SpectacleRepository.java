package com.example.spectacle.repository;

import com.example.spectacle.model.Spectacle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpectacleRepository extends JpaRepository<Spectacle,Long>, JpaSpecificationExecutor<Spectacle> {

    @Query("select s from Spectacle s where s.titre like :x")
    Page<Spectacle> chercher(@Param("x") String mc, Pageable pageable);
}

package com.example.spectacle.repository;


import com.example.spectacle.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestRepo extends JpaRepository<Test,Long>, JpaSpecificationExecutor<Test> {

}

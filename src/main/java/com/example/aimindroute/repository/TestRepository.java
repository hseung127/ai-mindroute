package com.example.aimindroute.repository;

import com.example.aimindroute.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
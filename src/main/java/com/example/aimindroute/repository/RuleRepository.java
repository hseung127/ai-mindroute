package com.example.aimindroute.repository;

import com.example.aimindroute.entity.rule.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}

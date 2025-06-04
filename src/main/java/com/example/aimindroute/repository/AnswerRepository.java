package com.example.aimindroute.repository;

import com.example.aimindroute.entity.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

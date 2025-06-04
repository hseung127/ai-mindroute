package com.example.aimindroute.repository;

import com.example.aimindroute.entity.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTestIdOrderByIdAsc(Long testId);
}

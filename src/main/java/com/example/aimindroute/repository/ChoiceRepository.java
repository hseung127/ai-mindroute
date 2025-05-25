package com.example.aimindroute.repository;

import com.example.aimindroute.entity.question.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    // question_id로 연결된 선택지들을 id 오름차순으로 정렬해서 조회
    List<Choice> findByQuestion_IdOrderByIdAsc(Long questionId);
}

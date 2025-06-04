package com.example.aimindroute.repository;

import com.example.aimindroute.entity.score.ScoreState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreStateRepository extends JpaRepository<ScoreState, Long> {
    // Optional 사용 이유: null 방지
    Optional<ScoreState> findBySessionIdAndDimension(Long sessionId, String dimension);
}

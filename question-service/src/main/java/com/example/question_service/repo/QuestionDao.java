package com.example.question_service.repo;

import com.example.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {
    List<Question> findByTopic(String topic);
    @Query(value = "SELECT q.id FROM quiz.question q Where q.topic=:topic order by RANDOM() LIMIT :num", nativeQuery = true)
    List<Long> createRandomQuizByCategory(String topic, int num);
}
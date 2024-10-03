package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.infrastructure.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {
}

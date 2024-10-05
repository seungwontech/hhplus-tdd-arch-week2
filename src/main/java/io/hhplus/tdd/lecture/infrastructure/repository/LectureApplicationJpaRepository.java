package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.infrastructure.entity.LectureApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplication, Long> {
    LectureApplication findByLectureItemIdAndUserId(Long lectureItemId, Long userId);

    List<LectureApplication> findByUserId(Long userId);
}

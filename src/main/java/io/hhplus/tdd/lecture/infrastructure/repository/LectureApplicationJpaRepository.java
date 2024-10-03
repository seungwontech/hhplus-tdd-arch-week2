package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.infrastructure.entity.LectureApplication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplication, Long> {

    LectureApplication findByLectureItemIdAndUserId(Long lectureItemId, Long userId);

}

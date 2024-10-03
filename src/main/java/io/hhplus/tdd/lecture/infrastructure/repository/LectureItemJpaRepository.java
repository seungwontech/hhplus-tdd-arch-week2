package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.infrastructure.entity.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LectureItemJpaRepository extends JpaRepository<LectureItem, Long> {

    List<LectureItem> findByLectureId(Long lectureId);

    LectureItem findByLectureIdAndLectureDate(Long lectureId, Date lectureDate);

}

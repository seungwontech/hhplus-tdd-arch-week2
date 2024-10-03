package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.domain.model.LectureItemDTO;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LectureItemJpaRepository extends JpaRepository<LectureItem, Long> {

    List<LectureItem> findByLectureId(Long lectureId);

    LectureItem findByLectureIdAndDate(Long lectureId, LocalDate lectureDate);

    List<LectureItem> findByDate(LocalDate date);

    LectureItem getLectureItemById(long lectureItemId);
}

package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.domain.model.LectureInventoryDTO;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureInventoryJpaRepository extends JpaRepository<LectureInventory, Long> {

    List<LectureInventoryDTO> findByLectureId(Long lectureId);

    LectureInventoryDTO findByLectureIdAndLectureItemId(Long lectureId, Long lectureItemId);
}

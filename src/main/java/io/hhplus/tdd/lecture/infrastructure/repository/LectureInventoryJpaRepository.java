package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.infrastructure.entity.LectureInventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureInventoryJpaRepository extends JpaRepository<LectureInventory, Long> {

    List<LectureInventory> findByLectureId(Long lectureId);

    LectureInventory findByLectureIdAndLectureItemId(Long lectureId, Long lectureItemId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT li FROM LectureInventory li WHERE li.lectureItemId = :lectureItemId")
    LectureInventory findByLectureItemId(Long lectureItemId);
}

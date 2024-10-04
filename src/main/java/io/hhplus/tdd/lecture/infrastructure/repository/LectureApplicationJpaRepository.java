package io.hhplus.tdd.lecture.infrastructure.repository;

import io.hhplus.tdd.lecture.infrastructure.entity.LectureApplication;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplication, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT li FROM LectureApplication li WHERE li.lectureItemId = :lectureItemId AND li.userId = :userId")
    LectureApplication findByLectureItemIdAndUserId(Long lectureItemId, Long userId);

    List<LectureApplication> findByUserId(Long userId);
}

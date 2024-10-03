package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureInventoryDTO;
import io.hhplus.tdd.lecture.domain.repository.LectureInventoryRepository;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureInventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureInventoryRepositoryImpl implements LectureInventoryRepository {

    private final LectureInventoryJpaRepository lectureInventoryJpaRepository;

    @Override
    public List<LectureInventoryDTO> getLectureInventories(Long lectureId) {

        return lectureInventoryJpaRepository.findByLectureId(lectureId);
    }

    @Override
    public LectureInventoryDTO getLectureInventory(Long lectureId, Long lectureItemId) {
        return lectureInventoryJpaRepository.findByLectureIdAndLectureItemId(lectureId, lectureItemId);
    }
}

package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureInventoryDTO;

import java.util.List;

public interface LectureInventoryRepository {

    List<LectureInventoryDTO> getLectureInventories(Long lectureId);

    LectureInventoryDTO getLectureInventory(Long lectureId, Long lectureItemId);

    LectureInventoryDTO getAmount(Long lectureItemId);

    void save(LectureInventoryDTO lectureInventoryDTO);
}

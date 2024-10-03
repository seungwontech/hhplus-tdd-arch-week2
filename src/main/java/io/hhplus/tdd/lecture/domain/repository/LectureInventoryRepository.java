package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureInventoryDTO;
import io.hhplus.tdd.lecture.domain.model.LectureItemDTO;

import java.util.List;

public interface LectureInventoryRepository {

    List<LectureInventoryDTO> getLectureInventories(Long lectureId);

    LectureInventoryDTO getLectureInventory(Long lectureId, Long lectureItemId);

}

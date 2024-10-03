package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureItemDTO;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LectureItemRepository {

    List<LectureItemDTO> getLectureItems(Long lectureId);

    LectureItemDTO getLectureItem(Long lectureId, LocalDate lectureDate);

    List<LectureItemDTO> getAvailableLecturesByDate(LocalDate date);

    LectureItemDTO getLectureItemById(long lectureItemId);
}

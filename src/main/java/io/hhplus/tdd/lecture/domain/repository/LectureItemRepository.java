package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureItemDTO;

import java.time.LocalDate;
import java.util.List;

public interface LectureItemRepository {

    List<LectureItemDTO> getLectureItems(Long lectureId);

    LectureItemDTO getLectureItem(Long lectureId, LocalDate lectureDate);

    List<LectureItemDTO> getAvailableLecturesByDate(LocalDate date);

    LectureItemDTO getLectureItemById(long lectureItemId);
}

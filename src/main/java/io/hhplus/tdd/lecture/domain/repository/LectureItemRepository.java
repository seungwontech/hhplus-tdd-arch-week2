package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureItemDTO;

import java.util.Date;
import java.util.List;

public interface LectureItemRepository {

    List<LectureItemDTO> getLectureItems(Long lectureId);

    LectureItemDTO getLectureItem(Long lectureId, Date lectureDate);

}

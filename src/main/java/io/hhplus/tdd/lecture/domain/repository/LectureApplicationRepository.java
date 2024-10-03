package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureApplicationDTO;

import java.util.List;


public interface LectureApplicationRepository {

    LectureApplicationDTO getLectureApplication(Long lectureItemId, Long userId);

    LectureApplicationDTO save(LectureApplicationDTO lectureApplicationDTO);

    List<LectureApplicationDTO> getLectureApplications(Long userId);
}

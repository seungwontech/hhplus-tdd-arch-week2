package io.hhplus.tdd.lecture.domain.repository;

import io.hhplus.tdd.lecture.domain.model.LectureDTO;

public interface LectureRepository {

    LectureDTO getLecture(Long lectureId);

}

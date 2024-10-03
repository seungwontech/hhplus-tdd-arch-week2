package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureDTO;
import io.hhplus.tdd.lecture.infrastructure.entity.Lecture;
import io.hhplus.tdd.lecture.domain.repository.LectureRepository;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public LectureDTO getLecture(Long lectureId) {

        Optional<Lecture> result = lectureJpaRepository.findById(lectureId);

        return convertToDTO(result.get());
    }

    private LectureDTO convertToDTO(Lecture lecture) {
        return LectureDTO.builder()
                .id(lecture.getId())
                .name(lecture.getName())
                .instructor(lecture.getInstructor())
                .build();
    }

}

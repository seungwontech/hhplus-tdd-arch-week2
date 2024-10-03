package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureApplicationDTO;
import io.hhplus.tdd.lecture.domain.repository.LectureApplicationRepository;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureApplication;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {

    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public LectureApplicationDTO getLectureApplication(Long lectureItemId, Long userId) {
        LectureApplication result = lectureApplicationJpaRepository.findByLectureItemIdAndUserId(lectureItemId, userId);

        return convertToDTO(result);
    }

    @Override
    public LectureApplicationDTO save(LectureApplicationDTO lectureApplicationDTO) {
        LectureApplication lectureApplication = LectureApplication.builder()
                .lectureItemId(lectureApplicationDTO.getLectureItemId())
                .userId(lectureApplicationDTO.getUserId())
                .build();

        LectureApplication savedLectureApplication = lectureApplicationJpaRepository.save(lectureApplication);

        return convertToDTO(savedLectureApplication);
    }

    private LectureApplicationDTO convertToDTO(LectureApplication lectureApplication) {
        return LectureApplicationDTO.builder()
                .lectureItemId(lectureApplication.getLectureItemId())
                .userId(lectureApplication.getUserId())
                .id(lectureApplication.getId())
                .build();
    }
}

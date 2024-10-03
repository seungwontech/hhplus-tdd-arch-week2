package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureApplicationDTO;
import io.hhplus.tdd.lecture.domain.repository.LectureApplicationRepository;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureApplication;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {

    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public LectureApplicationDTO getLectureApplication(Long lectureItemId, Long userId) {
        LectureApplication result = lectureApplicationJpaRepository.findByLectureItemIdAndUserId(lectureItemId, userId);
        if (result == null) {
            return null;
        }
        return convertToDTO(result);
    }

    @Override
    public LectureApplicationDTO save(LectureApplicationDTO lectureApplicationDTO) {
        LectureApplication lectureApplication = LectureApplication.builder()
                .lectureItemId(lectureApplicationDTO.getLectureItemId())
                .userId(lectureApplicationDTO.getUserId())
                .applicationDate(LocalDateTime.now())
                .build();

        LectureApplication savedLectureApplication = lectureApplicationJpaRepository.save(lectureApplication);
        System.out.println(savedLectureApplication.getUserId());
        return convertToDTO(savedLectureApplication);
    }

    private LectureApplicationDTO convertToDTO(LectureApplication lectureApplication) {
        return LectureApplicationDTO.builder()
                .id(lectureApplication.getId())
                .lectureItemId(lectureApplication.getLectureItemId())
                .userId(lectureApplication.getUserId())
                .applicationDate(lectureApplication.getApplicationDate())
                .build();
    }
}

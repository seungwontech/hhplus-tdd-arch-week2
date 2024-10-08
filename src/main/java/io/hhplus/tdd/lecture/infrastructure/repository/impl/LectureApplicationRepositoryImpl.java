package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureApplicationDTO;
import io.hhplus.tdd.lecture.domain.repository.LectureApplicationRepository;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureApplication;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        return convertToDTO(savedLectureApplication);
    }

    @Override
    public List<LectureApplicationDTO> getLectureApplications(Long userId) {
        List<LectureApplication> lectureApplications = lectureApplicationJpaRepository.findByUserId(userId);
        if (lectureApplications == null) {
            return null;
        }
        List<LectureApplicationDTO> result = new ArrayList<>();

        for (LectureApplication lectureApplication : lectureApplications) {
            result.add(convertToDTO(lectureApplication));
        }

        return result;
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

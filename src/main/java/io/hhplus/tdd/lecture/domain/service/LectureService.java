package io.hhplus.tdd.lecture.domain.service;

import io.hhplus.tdd.lecture.domain.exception.LectureErrorResult;
import io.hhplus.tdd.lecture.domain.exception.LectureException;
import io.hhplus.tdd.lecture.domain.model.*;
import io.hhplus.tdd.lecture.domain.repository.LectureApplicationRepository;
import io.hhplus.tdd.lecture.domain.repository.LectureInventoryRepository;
import io.hhplus.tdd.lecture.domain.repository.LectureItemRepository;
import io.hhplus.tdd.lecture.domain.repository.LectureRepository;
import io.hhplus.tdd.lecture.presentation.dto.LectureApplicationAddResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    private final LectureApplicationRepository lectureApplicationRepository;

    private final LectureItemRepository lectureItemRepository;

    private final LectureInventoryRepository lectureInventoryRepository;

    public LectureApplicationDTO getLectureApplication(Long lectureItemId, Long userId) {

        LectureApplicationDTO lectureApplication = lectureApplicationRepository.getLectureApplication(lectureItemId, userId);

        if (lectureApplication == null) {
            throw new LectureException(LectureErrorResult.LECTURE_APPLICATION_NOT_FOUND);
        }

        return lectureApplication;
    }

    @Transactional
    public LectureApplicationAddResponse applicationForLecture(Long lectureItemId, Long userId) {

        LectureInventoryDTO lectureInventoryDTO = getAmount(lectureItemId);

        LectureApplicationDTO result = lectureApplicationRepository.getLectureApplication(lectureItemId, userId);

        if (result != null) {
            throw new LectureException(LectureErrorResult.DUPLICATE_LECTURE_APPLICATION);
        }

        LectureApplicationDTO lectureApplicationDTO = LectureApplicationDTO.builder()
                .lectureItemId(lectureItemId)
                .userId(userId)
                .build();

        LectureApplicationDTO savedLectureApplication = lectureApplicationRepository.save(lectureApplicationDTO);

        updateAmount(lectureInventoryDTO);

        return LectureApplicationAddResponse.builder()
                .id(savedLectureApplication.getId())
                .applicationDate(savedLectureApplication.getApplicationDate())
                .build();
    }

    // 특강 세부 항목 조회
    public List<LectureItemDTO> getLectureItems(Long lectureId) {
        return lectureItemRepository.getLectureItems(lectureId);
    }

    // 특강 세부 상세 조회
    public LectureItemDTO getLectureItem(Long lectureId, LocalDate lectureDate) {
        return lectureItemRepository.getLectureItem(lectureId, lectureDate);
    }

    // 특강 인원 조회
    public List<LectureInventoryDTO> getLectureInventories(Long lectureId) {
        return lectureInventoryRepository.getLectureInventories(lectureId);
    }

    // 특강 인원 상세 조회
    public LectureInventoryDTO getLectureInventory(Long lectureId, Long lectureItemId) {
        return lectureInventoryRepository.getLectureInventory(lectureId, lectureItemId);
    }

    // 특강 조회
    public LectureDTO getLecture(Long lectureId) {

        LectureDTO result = lectureRepository.getLecture(lectureId);

        if (result == null) {
            throw new LectureException(LectureErrorResult.LECTURE_NOT_FOUND);
        }

        return result;
    }

    public List<LectureDetailDTO> getAvailableLecturesByDate(LocalDate date) {

        List<LectureItemDTO> lectureItems = lectureItemRepository.getAvailableLecturesByDate(date);

        if (lectureItems == null) {
            throw new LectureException(LectureErrorResult.LECTURE_ITEM_NOT_FOUND);
        }
        List<LectureDetailDTO> lectureDetails = new ArrayList<>();
        for (LectureItemDTO info : lectureItems) {

            LectureDTO lectureDTO = getLecture(info.getLectureId());

            LectureInventoryDTO lectureInventoryDTO = getLectureInventory(info.getLectureId(), info.getId());

            LectureDetailDTO lectureDetailDTO = LectureDetailDTO.builder()
                    .lectureId(lectureDTO.getId())
                    .lectureItemId(info.getId())
                    .lectureDate(info.getDate())
                    .instructor(lectureDTO.getInstructor())
                    .capacity(info.getCapacity())
                    .remainingSeats(lectureInventoryDTO.getAmount())
                    .build();
            lectureDetails.add(lectureDetailDTO);
        }

        return lectureDetails;
    }

    public List<LectureApplicationDTO> getLectureApplications(Long userId) {

        List<LectureApplicationDTO> lectureApplications = lectureApplicationRepository.getLectureApplications(userId);

        if (lectureApplications == null) {
            throw new LectureException(LectureErrorResult.LECTURE_APPLICATION_NOT_FOUND);
        }

        return lectureApplications;
    }

    public List<LectureApplicationDetailDTO> getCompletedApplicationsByUserId(Long userId) {
        List<LectureApplicationDTO> lectureApplications = getLectureApplications(userId);

        List<LectureApplicationDetailDTO> lectureApplicationDetailDTOs = new ArrayList<>();
        for (LectureApplicationDTO info : lectureApplications) {

            LectureItemDTO lectureItem = getLectureItemById(info.getLectureItemId());

            LectureDTO lecture = getLecture(lectureItem.getLectureId());

            LectureApplicationDetailDTO lectureApplicationDetailDTO = LectureApplicationDetailDTO.builder()
                    .lectureId(lecture.getId())
                    .lectureName(lecture.getName())
                    .instructor(lecture.getInstructor())
                    .build();
            lectureApplicationDetailDTOs.add(lectureApplicationDetailDTO);
        }

        return lectureApplicationDetailDTOs;
    }

    public LectureItemDTO getLectureItemById(long lectureItemId) {

        LectureItemDTO lectureItemDTO = lectureItemRepository.getLectureItemById(lectureItemId);
        if (lectureItemDTO == null) {
            throw new LectureException(LectureErrorResult.LECTURE_ITEM_NOT_FOUND);
        }

        return lectureItemDTO;
    }

    public LectureInventoryDTO getAmount(Long lectureItemId) {
        LectureInventoryDTO lectureInventoryDTO= lectureInventoryRepository.getAmount(lectureItemId);
        if (lectureInventoryDTO.getAmount() <= 0) {
            throw new LectureException(LectureErrorResult.LECTURE_CAPACITY_EXCEEDED);
        }
        return lectureInventoryDTO;
    }

    public void updateAmount(LectureInventoryDTO lectureInventoryDTO) {
        lectureInventoryRepository.save(lectureInventoryDTO);
    }
}

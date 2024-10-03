package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureInventoryDTO;
import io.hhplus.tdd.lecture.domain.repository.LectureInventoryRepository;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureInventory;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureInventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureInventoryRepositoryImpl implements LectureInventoryRepository {

    private final LectureInventoryJpaRepository lectureInventoryJpaRepository;

    @Override
    public List<LectureInventoryDTO> getLectureInventories(Long lectureId) {
        List<LectureInventory> lectureInventories = lectureInventoryJpaRepository.findByLectureId(lectureId);

        if (lectureInventories == null) {
            return null;
        }
        List<LectureInventoryDTO> result = new ArrayList<>();

        for (LectureInventory lectureInventory : lectureInventories) {
            result.add(convertToDTO(lectureInventory));
        }

        return result;
    }

    @Override
    public LectureInventoryDTO getLectureInventory(Long lectureId, Long lectureItemId) {
        LectureInventory result = lectureInventoryJpaRepository.findByLectureIdAndLectureItemId(lectureId, lectureItemId);
        return convertToDTO(result);
    }

    @Override
    public LectureInventoryDTO getAmount(Long lectureItemId) {
        LectureInventory result = lectureInventoryJpaRepository.findByLectureItemId(lectureItemId);
        return convertToDTO(result);
    }

    @Override
    public void save(LectureInventoryDTO lectureInventoryDTO) {
        LectureInventory lectureInventory = LectureInventory.builder()
                .lectureItemId(lectureInventoryDTO.getLectureItemId())
                .lectureId(lectureInventoryDTO.getLectureId())
                .id(lectureInventoryDTO.getId())
                .amount(lectureInventoryDTO.getAmount() - 1)
                .build();

        lectureInventoryJpaRepository.save(lectureInventory);
    }

    private LectureInventoryDTO convertToDTO(LectureInventory lectureInventory) {
        return LectureInventoryDTO.builder()
                .id(lectureInventory.getId())
                .lectureItemId(lectureInventory.getLectureItemId())
                .lectureId(lectureInventory.getLectureId())
                .amount(lectureInventory.getAmount())
                .build();
    }
}

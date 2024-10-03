package io.hhplus.tdd.lecture.infrastructure.repository.impl;

import io.hhplus.tdd.lecture.domain.model.LectureItemDTO;
import io.hhplus.tdd.lecture.domain.repository.LectureItemRepository;
import io.hhplus.tdd.lecture.infrastructure.entity.LectureItem;
import io.hhplus.tdd.lecture.infrastructure.repository.LectureItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureItemRepositoryImpl implements LectureItemRepository {

    private final LectureItemJpaRepository lectureItemJpaRepository;

    @Override
    public List<LectureItemDTO> getLectureItems(Long lectureId) {

        List<LectureItem> lectureItems = lectureItemJpaRepository.findByLectureId(lectureId);

        List<LectureItemDTO> result = new ArrayList<>();

        for (LectureItem lectureItem : lectureItems) {
            result.add(convertToDTO(lectureItem));
        }

        return result;
    }

    @Override
    public LectureItemDTO getLectureItem(Long lectureId, Date Date) {

        LectureItem result = lectureItemJpaRepository.findByLectureIdAndDate(lectureId, Date);

        return convertToDTO(result);
    }

    private LectureItemDTO convertToDTO(LectureItem lectureItem) {
        return LectureItemDTO.builder()
                .id(lectureItem.getId())
                .lectureId(lectureItem.getLectureId())
                .date(lectureItem.getDate())
                .capacity(lectureItem.getCapacity())
                .build();
    }
}

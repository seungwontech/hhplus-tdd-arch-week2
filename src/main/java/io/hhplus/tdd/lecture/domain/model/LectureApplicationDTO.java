package io.hhplus.tdd.lecture.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureApplicationDTO {

    private Long id;
    private Long lectureItemId;
    private Long userId;
    private LocalDateTime applicationDate; // 신청 날짜

    @Builder
    public LectureApplicationDTO(Long id, Long lectureItemId, Long userId) {
        this.id = id;
        this.lectureItemId = lectureItemId;
        this.userId = userId;
    }
}
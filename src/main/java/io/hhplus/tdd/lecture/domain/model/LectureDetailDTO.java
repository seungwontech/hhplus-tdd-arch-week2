package io.hhplus.tdd.lecture.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureDetailDTO {

    private Long lectureId;
    private Long lectureItemId;
    private LocalDate lectureDate;    // 강의 날짜
    private String instructor;    // 강사 이름
    private int capacity;         // 정원(최대 인원수)
    private int remainingSeats;   // 남은 좌석 수

    @Builder
    public LectureDetailDTO(Long lectureId, Long lectureItemId, LocalDate lectureDate, String instructor, int capacity, int remainingSeats) {
        this.lectureId = lectureId;
        this.lectureItemId = lectureItemId;
        this.lectureDate = lectureDate;
        this.instructor = instructor;
        this.capacity = capacity;
        this.remainingSeats = remainingSeats;
    }
}
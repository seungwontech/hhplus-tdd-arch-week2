package io.hhplus.tdd.lecture.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureItemDTO {

    private Long id;
    private Long lectureId;
    private Date date;         // 강의 날짜
    private int capacity;      // 정원(최대 인원수)

    @Builder
    public LectureItemDTO(Long id, Long lectureId, Date date, int capacity) {
        this.id = id;
        this.lectureId = lectureId;
        this.date = date;
        this.capacity = capacity;
    }
}

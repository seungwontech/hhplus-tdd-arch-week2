package io.hhplus.tdd.lecture.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureApplicationDetailDTO {

    private Long lectureId;
    private String lectureName;
    private String instructor;

    @Builder
    public LectureApplicationDetailDTO(Long lectureId, String lectureName, String instructor) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.instructor = instructor;
    }
}
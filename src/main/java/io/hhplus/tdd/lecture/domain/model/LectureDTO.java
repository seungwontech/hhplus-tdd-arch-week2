package io.hhplus.tdd.lecture.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureDTO {

    private Long id;
    private String name;
    private String instructor; // 강사 이름

    @Builder
    public LectureDTO(Long id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }
}

package io.hhplus.tdd.lecture.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureInventoryDTO {

    private Long id;
    private Long lectureId;
    private Long lectureItemId;
    private int amount;            // 잔여 수량

    @Builder
    public LectureInventoryDTO(Long id, Long lectureId, Long lectureItemId, int amount) {
        this.id = id;
        this.lectureId = lectureId;
        this.lectureItemId = lectureItemId;
        this.amount = amount;
    }
}

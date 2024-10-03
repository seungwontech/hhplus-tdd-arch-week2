package io.hhplus.tdd.lecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "LECTURE_INVENTORY")
public class LectureInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lectureId;

    @Column(nullable = false)
    private Long lectureItemId;

    @Column(nullable = false)
    private int amount; // 잔여 수량

    @Builder
    private LectureInventory(Long id, Long lectureId, Long lectureItemId, int amount) {
        this.id = id;
        this.lectureId = lectureId;
        this.lectureItemId = lectureItemId;
        this.amount = amount;
    }
}

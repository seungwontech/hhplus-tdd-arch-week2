package io.hhplus.tdd.lecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "LECTURE_APPLICATION")
public class LectureApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lectureItemId; // lectureItemId를 Long 타입으로 변경

    @Column(nullable = false)
    private Long userId; // 사용자 ID

    @Column(nullable = false)
    private LocalDateTime applicationDate; // 신청 날짜

    @Builder
    public LectureApplication(Long id, Long lectureItemId, Long userId) {
        this.id = id;
        this.lectureItemId = lectureItemId;
        this.userId = userId;
    }
}

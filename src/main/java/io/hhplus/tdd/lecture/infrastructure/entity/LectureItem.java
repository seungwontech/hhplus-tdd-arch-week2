package io.hhplus.tdd.lecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "LECTURE_ITEM")
public class LectureItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lectureId;

    @Column(nullable = false)
    private Date date; // 강의 날짜

    @Column(nullable = false)
    private int capacity; // 정원(최대인원수)

    @Builder
    public LectureItem(Long id, Long lectureId, Date date, int capacity) {
        this.id = id;
        this.lectureId = lectureId;
        this.date = date;
        this.capacity = capacity;
    }
}

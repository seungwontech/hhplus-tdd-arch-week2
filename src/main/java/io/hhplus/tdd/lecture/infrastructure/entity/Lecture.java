package io.hhplus.tdd.lecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "LECTURE")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String instructor; // 강사 이름

    @Builder
    public Lecture(Long id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }
}

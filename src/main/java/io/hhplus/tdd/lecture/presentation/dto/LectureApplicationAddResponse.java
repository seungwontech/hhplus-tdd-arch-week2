package io.hhplus.tdd.lecture.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class LectureApplicationAddResponse {
    private final Long id;
    private final LocalDateTime applicationDate;
}
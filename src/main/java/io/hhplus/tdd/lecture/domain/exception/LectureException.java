package io.hhplus.tdd.lecture.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LectureException extends RuntimeException {
    private final LectureErrorResult lectureErrorResult;
}

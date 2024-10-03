package io.hhplus.tdd.lecture.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LectureErrorResult {
    LECTURE_NOT_FOUND(HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다."),
    LECTURE_APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "강의내역를 찾을 수 없습니다."),
    LECTURE_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "강의 세부 내역을 찾을 수 없습니다."),

    DUPLICATE_LECTURE_APPLICATION(HttpStatus.BAD_REQUEST, "강의를 이미 신청하셨습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

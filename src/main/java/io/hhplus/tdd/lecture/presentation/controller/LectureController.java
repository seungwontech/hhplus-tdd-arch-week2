package io.hhplus.tdd.lecture.presentation.controller;

import io.hhplus.tdd.lecture.domain.model.LectureApplicationDetailDTO;
import io.hhplus.tdd.lecture.domain.model.LectureDetailDTO;
import io.hhplus.tdd.lecture.domain.service.LectureService;
import io.hhplus.tdd.lecture.presentation.dto.LectureApplicationAddResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    /**
     * 특강 신청 API
     * - 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
     * - 동일한 신청자는 동일한 강의에 대해서 한 번의 수강 신청만 성공할 수 있습니다.
     * - 특강은 선착순 30명만 신청 가능합니다.
     * - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
     * @param lectureItemId
     * @param userId
     * @return
     */
    @PostMapping("/application/{lectureItemId}")
    public ResponseEntity<LectureApplicationAddResponse> applicationForLecture(@PathVariable Long lectureItemId, @RequestParam Long userId) {
        LectureApplicationAddResponse lectureApplicationAddResponse = lectureService.applicationForLecture(lectureItemId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(lectureApplicationAddResponse);
    }

    /**
     * 특강 선택 API
     * - 날짜별로 현재 신청 가능한 특강 목록을 조회하는 API 를 작성합니다.
     * - 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
     * @param date
     * @return
     */
    @GetMapping("/available")
    public ResponseEntity<List<LectureDetailDTO>> getAvailableLectures(@RequestParam("date") LocalDate date) {
        List<LectureDetailDTO> availableLectures = lectureService.getAvailableLecturesByDate(date);
        return ResponseEntity.ok(availableLectures);
    }

    /**
     * 특강 신청 완료 목록 조회 API
     * - 특정 userId로 신청 완료된 특강 목록을 조회하는 API를 작성합니다.
     * - 각 항목은 특강 ID 및 이름, 강연자 정보를 담고 있어야 합니다.
     * @param userId
     * @return
     */
    @GetMapping("/completed")
    public ResponseEntity<List<LectureApplicationDetailDTO>> getCompletedApplications(@RequestParam("userId") Long userId) {
        List<LectureApplicationDetailDTO> completedApplications = lectureService.getCompletedApplicationsByUserId(userId);
        return ResponseEntity.ok(completedApplications);
    }

}

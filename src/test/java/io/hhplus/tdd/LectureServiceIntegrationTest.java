package io.hhplus.tdd;

import io.hhplus.tdd.lecture.domain.service.LectureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LectureServiceIntegrationTest {

    @Autowired
    private LectureService lectureService;

    @Test
    public void 동시에동일한특강에대해40명이신청했을때30명만성공하는것을검증() throws InterruptedException {
        // 준비
        Long lectureItemId = 2L; // 테스트할 특강 아이디
        int totalUsers = 40; // 총 사용자 수
        CountDownLatch latch = new CountDownLatch(totalUsers);
        ExecutorService executorService = Executors.newFixedThreadPool(40);

        // 신청 성공 카운트
        AtomicInteger failedOperations = new AtomicInteger(0);
        AtomicInteger successCount = new AtomicInteger(0);

        for (int i = 0; i < totalUsers; i++) {
            final Long userId = (long) (100 + i);
            executorService.submit(() -> {
                try {
                    lectureService.applicationForLecture(lectureItemId, userId);
                    successCount.incrementAndGet(); // 성공 시 카운트 증가
                } catch (RuntimeException e) {
                    failedOperations.incrementAndGet();
                } finally {
                    latch.countDown(); // 모든 스레드가 종료될 때까지 대기
                }
            });
        }

        latch.await(); // 모든 스레드가 끝날 때까지 대기
        executorService.shutdown();

        // 검증
        assertEquals(30, successCount.get(), "동시 다발적으로 40명이 신청해서 30명이 성공해야 합니다.");
        assertEquals(10, failedOperations.get(), "동시 다발적으로 40명이 신청해서 10명이 실패해야 합니다.");

    }

    @Test
    public void 동일한유저로같은특강5번신청했을때1번만성공하는것을검증() throws InterruptedException {
        // given
        AtomicInteger failedOperations = new AtomicInteger(0);
        AtomicInteger successCount = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    lectureService.applicationForLecture(1L, 101L);
                    successCount.incrementAndGet();
                } catch (RuntimeException e) {
                    failedOperations.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
        // then
        assertEquals(1, successCount.get(), "성공 카운트가 1");
        assertEquals(4, failedOperations.get(), "실패 카운트가 4");
    }
}
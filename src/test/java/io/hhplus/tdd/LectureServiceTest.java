package io.hhplus.tdd;

import io.hhplus.tdd.lecture.domain.exception.LectureErrorResult;
import io.hhplus.tdd.lecture.domain.exception.LectureException;
import io.hhplus.tdd.lecture.domain.model.*;
import io.hhplus.tdd.lecture.domain.repository.LectureApplicationRepository;
import io.hhplus.tdd.lecture.domain.repository.LectureInventoryRepository;
import io.hhplus.tdd.lecture.domain.repository.LectureItemRepository;
import io.hhplus.tdd.lecture.domain.repository.LectureRepository;
import io.hhplus.tdd.lecture.domain.service.LectureService;
import io.hhplus.tdd.lecture.presentation.dto.LectureApplicationAddResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureApplicationRepository lectureApplicationRepository;

    @Mock
    private LectureItemRepository lectureItemRepository;

    @Mock
    private LectureInventoryRepository lectureInventoryRepository;

    @Mock
    private LectureRepository lectureRepository;

    private final Long userId = 101L;
    private final Long lectureItemId = 1L;
    private final Long lectureId = 1L;

    @Test
    public void 강의조회_성공() {
        // given
        LectureDTO lectureDTO = LectureDTO.builder().id(lectureId).name("스프링 특강").instructor("이강사").build();
        doReturn(lectureDTO).when(lectureRepository).getLecture(lectureId);

        // when
        LectureDTO result = lectureService.getLecture(lectureId);

        // then
        assertThat(result.getId()).isEqualTo(lectureId);
        assertThat(result.getName()).isEqualTo("스프링 특강");
        assertThat(result.getInstructor()).isEqualTo("이강사");
    }

    @Test
    public void 강의조회_실패_존재하지않음() {
        // given
        doReturn(null).when(lectureRepository).getLecture(lectureId);

        // when
        final LectureException result = assertThrows(LectureException.class, () -> lectureService.getLecture(lectureId));

        // then
        assertThat(result.getLectureErrorResult()).isEqualTo(LectureErrorResult.LECTURE_NOT_FOUND);
    }

    @Test
    public void 특강인원상세조회_성공() {
        // given
        LectureInventoryDTO lectureInventoryDTO = LectureInventoryDTO.builder().id(1L).lectureId(lectureId).lectureItemId(lectureItemId).amount(30).build();
        doReturn(lectureInventoryDTO).when(lectureInventoryRepository).getLectureInventory(lectureId, lectureItemId);

        // when
        LectureInventoryDTO result = lectureService.getLectureInventory(lectureId, lectureItemId);

        // then
        assertThat(result.getLectureId()).isEqualTo(lectureId);
        assertThat(result.getLectureItemId()).isEqualTo(lectureItemId);
    }

    @Test
    public void 특강인원조회_성공() {
        // given
        LectureInventoryDTO lectureInventoryDTO1 = LectureInventoryDTO.builder().id(1L).lectureId(1L).lectureItemId(1L).amount(30).build();
        LectureInventoryDTO lectureInventoryDTO2 = LectureInventoryDTO.builder().id(2L).lectureId(1L).lectureItemId(2L).amount(30).build();
        List<LectureInventoryDTO> lectureInventoryDTOs = List.of(lectureInventoryDTO1, lectureInventoryDTO2);
        doReturn(lectureInventoryDTOs).when(lectureInventoryRepository).getLectureInventories(lectureId);

        // when
        List<LectureInventoryDTO> result = lectureService.getLectureInventories(lectureId);

        // then
        assertThat(result.get(0).getLectureId()).isEqualTo(lectureId);
        assertThat(result.get(0).getLectureItemId()).isEqualTo(1L);
        assertThat(result.get(1).getLectureId()).isEqualTo(lectureId);
        assertThat(result.get(1).getLectureItemId()).isEqualTo(2L);
    }

    @Test
    public void 특강세부항목날짜로조회_성공() {
        // given
        LocalDate lectureDate1 = LocalDate.parse("2024-10-03");

        LectureDTO lectureDTO = LectureDTO.builder().id(lectureId).name("스프링 특강").instructor("이강사").build();

        LectureInventoryDTO lectureInventoryDTO = LectureInventoryDTO.builder().id(1L).lectureId(lectureId).lectureItemId(lectureItemId).amount(30).build();

        LectureItemDTO lectureItemDTO1 = LectureItemDTO.builder().id(1L).lectureId(lectureId).date(lectureDate1).capacity(30).build();
        List<LectureItemDTO> lectureItemDTOs = List.of(lectureItemDTO1);

        doReturn(lectureDTO).when(lectureRepository).getLecture(lectureId);
        doReturn(lectureInventoryDTO).when(lectureInventoryRepository).getLectureInventory(lectureId, lectureItemId);
        doReturn(lectureItemDTOs).when(lectureItemRepository).getAvailableLecturesByDate(lectureDate1);

        // when
        List<LectureDetailDTO> result = lectureService.getAvailableLecturesByDate(lectureDate1);

        // then
        assertThat(result.get(0).getLectureId()).isEqualTo(lectureId);
    }



    @Test
    public void 특강세부항목상세조회_성공() {
        // given
        LocalDate lectureDate = LocalDate.parse("2024-10-03");

        LectureItemDTO lectureItemDTO = LectureItemDTO.builder().id(1L).lectureId(lectureId).date(lectureDate).capacity(30).build();
        doReturn(lectureItemDTO).when(lectureItemRepository).getLectureItem(lectureId, lectureDate);

        // when
        LectureItemDTO result = lectureService.getLectureItem(lectureId, lectureDate);

        // then
        assertThat(result.getLectureId()).isEqualTo(lectureId);
        assertThat(result.getDate()).isEqualTo(lectureDate);
    }

    @Test
    public void 특강세부항목조회_성공() {
        // given
        LocalDate lectureDate1 = LocalDate.parse("2024-10-03");
        LocalDate lectureDate2 = LocalDate.parse("2024-10-03");

        LectureItemDTO lectureItemDTO1 = LectureItemDTO.builder().id(1L).lectureId(lectureId).date(lectureDate1).capacity(30).build();
        LectureItemDTO lectureItemDTO2 = LectureItemDTO.builder().id(2L).lectureId(lectureId).date(lectureDate2).capacity(30).build();
        List<LectureItemDTO> lectureItemDTOs = List.of(lectureItemDTO1, lectureItemDTO2);
        doReturn(lectureItemDTOs).when(lectureItemRepository).getLectureItems(lectureId);

        // when
        List<LectureItemDTO> result = lectureService.getLectureItems(lectureId);

        // then
        assertThat(result.get(0).getLectureId()).isEqualTo(lectureId);
        assertThat(result.get(0).getDate()).isEqualTo(lectureDate1);
        assertThat(result.get(1).getLectureId()).isEqualTo(lectureId);
        assertThat(result.get(1).getDate()).isEqualTo(lectureDate2);
    }

    @Test
    public void 사용자_특강신청하기_성공() {
        // given
        LectureApplicationDTO savedLectureApplicationDTO = LectureApplicationDTO.builder()
                .id(-1L)  // 예시 ID
                .lectureItemId(lectureItemId)
                .userId(userId)
                .build();

        doReturn(null).when(lectureApplicationRepository).getLectureApplication(lectureItemId, userId);
        doReturn(savedLectureApplicationDTO).when(lectureApplicationRepository).save(any(LectureApplicationDTO.class));

        // when
        LectureApplicationAddResponse result = lectureService.applicationForLecture(lectureItemId, userId);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    public void 사용자_특강신청하기_실패_이미신청함() {
        // given
        doReturn(LectureApplicationDTO.builder().build()).when(lectureApplicationRepository).getLectureApplication(lectureItemId, userId);

        // when
        final LectureException result = assertThrows(LectureException.class, () -> lectureService.applicationForLecture(lectureItemId, userId));

        // then
        assertThat(result.getLectureErrorResult()).isEqualTo(LectureErrorResult.DUPLICATE_LECTURE_APPLICATION);
    }

    @Test
    public void 사용자_특강신청조회_성공() {
        // given
        LectureApplicationDTO lectureApplicationDTO = LectureApplicationDTO.builder().id(1L).lectureItemId(lectureItemId).userId(userId).build();

        doReturn(lectureApplicationDTO).when(lectureApplicationRepository).getLectureApplication(lectureItemId, userId);

        // when
        LectureApplicationDTO result = lectureService.getLectureApplication(lectureItemId, userId);

        // then
        assertThat(result.getLectureItemId()).isEqualTo(lectureItemId);
        assertThat(result.getUserId()).isEqualTo(userId);
    }

    @Test
    public void 사용자_특강신청조회_실패_존재하지않음() {
        // given
        doReturn(null).when(lectureApplicationRepository).getLectureApplication(lectureItemId, userId);

        // when
        final LectureException result = assertThrows(LectureException.class, () -> lectureService.getLectureApplication(lectureItemId, userId));

        // then
        assertThat(result.getLectureErrorResult()).isEqualTo(LectureErrorResult.LECTURE_APPLICATION_NOT_FOUND);
    }
}

package page.time.api.domain.lecture.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import page.time.api.domain.lecture.application.LectureRetrieveService;
import page.time.api.domain.lecture.domain.Type;
import page.time.api.domain.lecture.dto.response.LectureResponseDto;
import page.time.api.domain.lecture.dto.response.LectureSelectResponseDto;
import page.time.api.global.common.ApiResponse;
import page.time.api.global.common.CursorResult;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lecture/retrieve")
@Tag(name = "Lecture - Retrieve", description = "과목 조회")
public class LectureRetrieveController {

    private final LectureRetrieveService lectureRetrieveService;

    @Operation(summary = "필터링된 과목 조회", description =
            "8개의 파라미터를 자유롭게 조합하여 필터링 가능<br>" +
            "모든 파라미터를 하나라도 입력하지 않으면 전체 조회됨<br>")
    @GetMapping("")
    public ApiResponse<CursorResult<LectureResponseDto>> retrieveLectures(
            @RequestParam(name = "campus", required = false) String campus,
            @RequestParam(name = "type", required = false) Type type,
            @RequestParam(name = "grade", required = false) Integer grade,
            @RequestParam(name = "day", required = false) List<String> day,
            @RequestParam(name = "time", required = false) List<String> time,
            @RequestParam(name = "major", required = false) String major,
            @RequestParam(name = "isExceeded", required = false) Boolean isExceeded,
            @RequestParam(name = "lectureName", required = false) String lectureName,
            @RequestParam(name = "cursor", required = false) Long cursor,
            @RequestParam(name = "limit", defaultValue = "50") int limit
    ) {
        CursorResult<LectureResponseDto> lectures = lectureRetrieveService.retrieveLectures(
                campus, type, grade, day, time, major, isExceeded, lectureName, cursor, limit
        );
        return ApiResponse.success(lectures);
    }

    @Operation(summary = "전공 이름 조회", description =
            "검색된 결과를 토대로 완전한 전공 이름을 조회함<br>" +
            "입력하지 않으면 전체 조회됨<br>")
    @GetMapping("/major")
    public ApiResponse<List<String>> retrieveAllMajor(
            @RequestParam(name = "major", required = false) String major
    ) {
        List<String> majors = lectureRetrieveService.retrieveMajor(major);
        return ApiResponse.success(majors);
    }

    @Operation(summary = "선택한 강의 id 리스트에 맞는 강의 정보 조회")
    @GetMapping("/select")
    public ApiResponse<List<LectureSelectResponseDto>> retrieveLecturesById(
            @RequestParam List<Long> lectureIds
    ) {
        List<LectureSelectResponseDto> lectures = lectureRetrieveService.retrieveSelectedLecturesByIds(lectureIds);
        return ApiResponse.success(lectures);
    }
}

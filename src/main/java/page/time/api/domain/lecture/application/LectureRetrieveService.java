package page.time.api.domain.lecture.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import page.time.api.domain.lecture.dao.LectureRepository;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;
import page.time.api.domain.lecture.dto.response.LectureResponseDto;
import page.time.api.domain.lecture.dto.response.LectureSelectResponseDto;
import page.time.api.global.common.CursorResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureRetrieveService {

    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    public CursorResult<LectureResponseDto> retrieveLectures(
            String campus, Type type, Integer grade, List<String> day, List<String> time, String major, Boolean isExceeded, String lectureName, Long cursor, int limit
    ) {
        List<Lecture> lectures = lectureRepository.findByFilter(campus, type, grade, day, time, major, isExceeded, lectureName, cursor, limit);
        List<LectureResponseDto> lectureDetailResponseDtos = lectures.stream()
                .map(LectureResponseDto::toDto)
                .collect(Collectors.toList());
        return CursorResult.of(lectureDetailResponseDtos, cursor, limit);
    }

    @Transactional(readOnly = true)
    public List<String> retrieveMajor(String major) {
        return lectureRepository.findByMajor(major);
    }

    @Transactional(readOnly = true)
    public List<LectureSelectResponseDto> retrieveSelectedLecturesByIds(List<Long> lectureIds) {
        return lectureIds.stream()
                .map(id -> lectureRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(LectureSelectResponseDto::toDto)
                .toList();
    }
}
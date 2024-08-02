package page.time.api.domain.lecture.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import page.time.api.domain.lecture.dao.LectureRepository;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;
import page.time.api.domain.lecture.dto.response.LectureResponseDto;
import page.time.api.global.common.CursorResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureRetrieveService {

    private final LectureRepository lectureRepository;

    public CursorResult<LectureResponseDto> retrieveLectures(
            String campus, Type type, Integer grade, List<String> day, List<String> time, String major, Boolean isExceeded, String lectureName, Long cursor, Pageable pageable
    ) {
        List<Lecture> lectures = lectureRepository.findByFilter(campus, type, grade, day, time, major, isExceeded, lectureName, cursor, pageable);
        List<LectureResponseDto> lectureDetailResponseDtos = lectures.stream()
                .map(LectureResponseDto::toDto)
                .collect(Collectors.toList());
        return new CursorResult<>(lectureDetailResponseDtos, checkLastPage(pageable, lectureDetailResponseDtos));
    }

    private boolean checkLastPage(Pageable pageable, List<LectureResponseDto> lectureDetailResponseDtos) {
        boolean hasNext = false;
        if (lectureDetailResponseDtos.size() > pageable.getPageSize()) {
            hasNext = true;
            lectureDetailResponseDtos.remove(pageable.getPageSize());
        }
        return hasNext;
    }
}
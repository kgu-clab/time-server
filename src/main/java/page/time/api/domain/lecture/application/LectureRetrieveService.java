package page.time.api.domain.lecture.application;

import lombok.RequiredArgsConstructor;
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
            String campus, Type type, Integer grade, List<String> day, List<String> time, String major, Boolean isExceeded, String lectureName, Long cursor, int limit
    ) {
        List<Lecture> lectures = lectureRepository.findByFilter(campus, type, grade, day, time, major, isExceeded, lectureName, cursor, limit);
        List<LectureResponseDto> lectureDetailResponseDtos = lectures.stream()
                .map(LectureResponseDto::toDto)
                .collect(Collectors.toList());
        return new CursorResult<>(lectureDetailResponseDtos, checkFirstPage(cursor, lectureDetailResponseDtos), checkLastPage(limit, lectureDetailResponseDtos));
    }

    private boolean checkFirstPage(Long cursor, List<LectureResponseDto> lectureDetailResponseDtos) {
        boolean hasPrevious = false;
        if ((cursor != null) && (lectureDetailResponseDtos.get(0).getId() > cursor)) {
            hasPrevious = true;
        }
        return hasPrevious;
    }

    private boolean checkLastPage(int limit, List<LectureResponseDto> lectureDetailResponseDtos) {
        boolean hasNext = false;
        if (lectureDetailResponseDtos.size() > limit) {
            hasNext = true;
            lectureDetailResponseDtos.remove(limit);
        }
        return hasNext;
    }
}
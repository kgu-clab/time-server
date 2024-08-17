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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureRetrieveService {

    private final LectureRepository lectureRepository;

    public CursorResult<LectureResponseDto> retrieveLectures(
            List<String> campuses, List<Type> types, List<Integer> grades, List<String> days, List<String> times, List<String> majors, Boolean isExceeded, String lectureName, Long cursor, int limit
    ) {
        List<Lecture> lectures = lectureRepository.findByFilter(campuses, types, grades, days, times, majors, isExceeded, lectureName, cursor, limit);
        List<LectureResponseDto> lectureDetailResponseDtos = lectures.stream()
                .map(LectureResponseDto::toDto)
                .collect(Collectors.toList());
        return CursorResult.of(lectureDetailResponseDtos, cursor, limit);
    }

    public List<String> retrieveMajor(String major) {
        return lectureRepository.findByMajor(major);
    }

    public List<LectureSelectResponseDto> retrieveSelectedLecturesByIds(List<Long> lectureIds) {
        return lectureIds.stream()
                .map(lectureRepository::findById)
                .flatMap(Optional::stream)
                .map(LectureSelectResponseDto::toDto)
                .toList();
    }
}
package page.time.api.domain.lecture.dao;

import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;

import java.util.List;

public interface LectureRepositoryCustom {

    List<Lecture> findByFilter(List<String> campuses, List<Type> types, List<Integer> grades, List<String> days, List<String> times, List<String> majors, Boolean isExceeded, String lectureName, Long cursor, int limit);

    List<String> findByMajor(String major);
}
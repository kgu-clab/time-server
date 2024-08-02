package page.time.api.domain.lecture.dao;

import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;

import java.util.List;

public interface LectureRepositoryCustom {

    List<Lecture> findByFilter(String campus, Type type, Integer grade, List<String> day, List<String> time, String major, Boolean isExceeded, String lectureName, Long cursor, int limit);
}
package page.time.api.domain.lecture.dao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;

import java.util.List;

import static page.time.api.domain.lecture.domain.QLecture.lecture;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<Lecture> findByFilter(String campus, Type type, Integer grade, List<String> day, List<String> time, String major, Boolean isExceeded, String lectureName, Long cursor, int limit) {
        return query
                .selectFrom(lecture)
                .where(
                        eqToCampus(campus),
                        eqToType(type),
                        eqToGrade(grade),
                        eqToDay(day),
                        eqToTime(time),
                        eqToMajor(major),
                        eqToIsExceeded(isExceeded),
                        containsToLectureName(lectureName),
                        gtCursor(cursor)
                )
                .limit(limit + 1)
                .fetch();
    }

    private BooleanExpression eqToCampus(String campus) {
        if (campus == null || campus.isEmpty()) {
            return null;
        }
        return lecture.campus.contains(campus);
    }

    private BooleanExpression eqToType(Type type) {
        if (type == null) {
            return null;
        }
        return lecture.type.eq(type);
    }

    private BooleanExpression eqToGrade(Integer grade) {
        if (grade == null) {
            return null;
        }
        return lecture.grade.eq(grade);
    }

    private BooleanBuilder eqToDay(List<String> days) {
        if (days == null || days.isEmpty()) {
            return null;
        }
        BooleanExpression isElearning = lecture.time.eq("이러닝").and(lecture.groupName.contains("이러닝"));
        BooleanBuilder builder = new BooleanBuilder(isElearning);
        days.stream()
                .map(day -> lecture.time.startsWith(day)
                        .and(lecture.groupName.contains("이러닝").not()))
                .forEach(builder::or);
        return builder;
    }

    private BooleanBuilder eqToTime(List<String> times) {
        if (times == null || times.isEmpty()) {
            return null;
        }
        BooleanBuilder builder = new BooleanBuilder();
        times.stream()
                .map(lecture.time::contains)
                .forEach(builder::or);
        return builder;
    }

    private BooleanExpression eqToMajor(String major) {
        if (major == null || major.isEmpty()) {
            return null;
        }
        return lecture.major.eq(major);
    }

    private BooleanExpression eqToIsExceeded(Boolean isExceeded) {
        if (isExceeded == null) {
            return null;
        }
        return lecture.isExceeded.eq(isExceeded);
    }

    private BooleanExpression containsToLectureName(String lectureName) {
        if (lectureName == null || lectureName.isEmpty()) {
            return null;
        }
        return lecture.name.containsIgnoreCase(lectureName);
    }

    private BooleanExpression gtCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return lecture.id.gt(cursor);
    }
}

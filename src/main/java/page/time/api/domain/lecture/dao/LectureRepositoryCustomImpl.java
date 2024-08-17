package page.time.api.domain.lecture.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;

import java.util.List;

import static page.time.api.domain.lecture.domain.QLecture.lecture;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory query;

    private static final String NONE = "None";

    @Override
    public List<Lecture> findByFilter(List<String> campuses, List<Type> types, List<Integer> grades, List<String> days, List<String> times, List<String> majors, Boolean isExceeded, String lectureName, Long cursor, int limit) {
        return query
                .selectFrom(lecture)
                .where(
                        containsToLectureName(lectureName),
                        eqToMajors(majors),
                        eqToDays(days),
                        eqToTimes(times),
                        eqToGrades(grades),
                        eqToTypes(types),
                        eqToCampuses(campuses),
                        eqToIsExceeded(isExceeded),
                        gtCursor(cursor)
                )
                .limit(limit + 1)
                .fetch();
    }

    @Override
    public List<String> findByMajor(String major) {
        return query
                .select(lecture.major)
                .distinct()
                .from(lecture)
                .where(
                        lecture.major.ne(NONE)
                                .and(eqToMajor(major))
                )
                .fetch();
    }

    private BooleanExpression eqToCampuses(List<String> campuses) {
        if (CollectionUtils.isEmpty(campuses)) {
            return null;
        }
        return campuses.stream()
                .map(lecture.campus::contains)
                .reduce(BooleanExpression::or)
                .orElse(null);
    }

    private BooleanExpression eqToTypes(List<Type> types) {
        if (CollectionUtils.isEmpty(types)) {
            return null;
        }
        return types.stream()
                .map(lecture.type::eq)
                .reduce(BooleanExpression::or)
                .orElse(null);
    }

    private BooleanExpression eqToGrades(List<Integer> grades) {
        if (CollectionUtils.isEmpty(grades)) {
            return null;
        }
        return grades.stream()
                .map(lecture.grade::eq)
                .reduce(BooleanExpression::or)
                .orElse(null);
    }

    private BooleanExpression eqToDays(List<String> days) {
        if (CollectionUtils.isEmpty(days)) {
            return null;
        }
        return days.stream()
                .map(lecture.time::contains)
                .reduce(BooleanExpression::or)
                .orElse(null);
    }

    private BooleanExpression eqToTimes(List<String> times) {
        if (CollectionUtils.isEmpty(times)) {
            return null;
        }
        return times.stream()
                .map(lecture.time::contains)
                .reduce(BooleanExpression::or)
                .orElse(null);
    }

    private BooleanExpression eqToMajor(String major) {
        if (major == null || major.isEmpty()) {
            return null;
        }
        return lecture.major.contains(major);
    }

    private BooleanExpression eqToMajors(List<String> majors) {
        if (CollectionUtils.isEmpty(majors)) {
            return null;
        }
        return majors.stream()
                .map(lecture.major::contains)
                .reduce(BooleanExpression::or)
                .orElse(null);
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
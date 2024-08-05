package page.time.api.global.common;

import lombok.Getter;

import java.util.List;

@Getter
public class CursorResult<T> {

    private final List<T> values;
    private final Boolean hasPrevious;
    private final Boolean hasNext;

    public CursorResult(List<T> values, Boolean hasPrevious, Boolean hasNext) {
        this.values = values;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
    }

    public static <T extends IdProvider> CursorResult<T> of(List<T> values, Long cursor, int limit) {
        boolean hasPrevious = checkFirstPageById(cursor, values);
        boolean hasNext = checkLastPageById(limit, values);
        return new CursorResult<>(values, hasPrevious, hasNext);
    }

    private static <T extends IdProvider> boolean checkFirstPageById(Long cursor, List<T> values) {
        if ((cursor != null) && (cursor > 0) && (!values.isEmpty()) && (values.get(0).getId() > cursor)) {
            return true;
        }
        return false;
    }

    private static <T extends IdProvider> boolean checkLastPageById(int limit, List<T> values) {
        if (values.size() > limit) {
            values.remove(limit);
            return true;
        }
        return false;
    }
}

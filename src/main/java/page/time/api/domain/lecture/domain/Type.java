package page.time.api.domain.lecture.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {

    CULTURE("culture","교양"),
    MAJOR("major", "전공"),
    TEACHING("teaching", "교직"),
    ROTC("rotc", "ROTC"),
    LINKEDFUSION("linkedfusion", "연계융합");

    private String key;
    private String description;
}

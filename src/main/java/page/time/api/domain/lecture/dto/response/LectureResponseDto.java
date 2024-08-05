package page.time.api.domain.lecture.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;
import page.time.api.global.common.IdProvider;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponseDto implements IdProvider {

    private Long id;
    private String campus;
    private String category;
    private String code;
    private Integer credit;
    private Integer grade;
    private String groupName;
    private Boolean isExceeded;
    private String major;
    private String name;
    private String professor;
    private String room;
    private Integer year;
    private String semester;
    private String time;
    private Type type;

    public static LectureResponseDto toDto(Lecture lecture) {
        return LectureResponseDto.builder()
                .id(lecture.getId())
                .campus(lecture.getCampus())
                .category(lecture.getCategory())
                .code(lecture.getCode())
                .credit(lecture.getCredit())
                .grade(lecture.getGrade())
                .groupName(lecture.getGroupName())
                .isExceeded(lecture.getIsExceeded())
                .major(lecture.getMajor())
                .name(lecture.getName())
                .professor(lecture.getProfessor())
                .room(lecture.getRoom())
                .year(lecture.getYear())
                .semester(lecture.getSemester())
                .time(lecture.getTime())
                .type(lecture.getType())
                .build();
    }
}

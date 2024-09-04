package page.time.api.domain.lecture.dto.response;

import lombok.Builder;
import lombok.Getter;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;

@Getter
@Builder
public class LectureSelectResponseDto {

    private Long id;
    private String campus;
    private Type type;
    private String category;
    private String name;
    private String professor;
    private String room;
    private String time;
    private Integer credit;

    public static LectureSelectResponseDto toDto(Lecture lecture) {
        return LectureSelectResponseDto.builder()
                .id(lecture.getId())
                .campus(lecture.getCampus())
                .type(lecture.getType())
                .category(lecture.getCategory())
                .name(lecture.getName())
                .professor(lecture.getProfessor())
                .room(lecture.getRoom())
                .time(lecture.getTime())
                .credit(lecture.getCredit())
                .build();
    }
}
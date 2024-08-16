package page.time.api.domain.lecture.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import page.time.api.domain.lecture.domain.Lecture;
import page.time.api.domain.lecture.domain.Type;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureSelectResponseDto {

    private Long id;
    private String campus;
    private Type type;
    private String category;
    private String name;
    private String professor;
    private String room;
    private String time;

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
                .build();
    }
}
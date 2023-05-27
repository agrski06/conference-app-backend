package conference.api.user.DTOs;

import conference.api.lecture.DTOs.LectureInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLecturesPreviewDTO {
    private String login;
    private String email;
    private List<LectureInfoDTO> lectures = new ArrayList<>();
}

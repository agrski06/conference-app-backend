package conference.api.lecture.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserForLectureRequest {
    private long lectureId;
    private String login;
    private String email;
}

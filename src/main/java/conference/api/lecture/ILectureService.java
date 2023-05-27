package conference.api.lecture;

import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.RegisterUserForLectureRequest;
import conference.api.lecture.DTOs.ScheduleDTO;
import conference.api.user.DTOs.UserLecturesPreviewDTO;

import java.util.List;

public interface ILectureService {
    ScheduleDTO getSchedule();
    List<LectureInfoDTO> getLecturesForUser(String login);
    UserLecturesPreviewDTO registerUserForLecture(RegisterUserForLectureRequest request);
}

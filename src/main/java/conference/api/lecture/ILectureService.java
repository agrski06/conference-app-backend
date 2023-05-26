package conference.api.lecture;

import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.RegisterUserRequest;
import conference.api.lecture.DTOs.ScheduleDTO;

import java.util.List;

public interface ILectureService {
    ScheduleDTO getSchedule();
    List<LectureInfoDTO> getLecturesForUser(String login);
    boolean registerUserForLecture(RegisterUserRequest request);
}

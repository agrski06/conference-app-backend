package conference.api.lecture;

import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.RegisterUserRequest;
import conference.api.lecture.DTOs.ScheduleDTO;

import java.util.Set;

public interface ILectureService {
    ScheduleDTO getSchedule();
    Set<LectureInfoDTO> getLecturesForUser(String login);
    boolean registerUserForLecture(RegisterUserRequest request);
}

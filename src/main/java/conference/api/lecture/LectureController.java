package conference.api.lecture;

import conference.api.exceptions.UserNotFoundException;
import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.RegisterUserForLectureRequest;
import conference.api.lecture.DTOs.ScheduleByTopicsDTO;
import conference.api.lecture.DTOs.ScheduleDTO;
import conference.api.user.DTOs.UserLecturesPreviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final ILectureService lectureService;

    @GetMapping("/schedule")
    public ResponseEntity<ScheduleDTO> getSchedule() {
        return ResponseEntity.ok(lectureService.getSchedule());
    }

    @GetMapping("/schedule/topics")
    public ResponseEntity<ScheduleByTopicsDTO> getSchedulesByTopics() {
        return ResponseEntity.ok(lectureService.getScheduleByTopics());
    }

    @GetMapping("/{login}")
    public ResponseEntity<List<LectureInfoDTO>> getLecturesForUser(@PathVariable String login) {
        List<LectureInfoDTO> result = lectureService.getLecturesForUser(login);
        if (result == null)
            throw new UserNotFoundException();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<UserLecturesPreviewDTO> registerUserForLecture(@RequestBody RegisterUserForLectureRequest request) {
        return ResponseEntity.ok(lectureService.registerUserForLecture(request));
    }

}

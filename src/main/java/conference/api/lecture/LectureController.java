package conference.api.lecture;

import conference.api.lecture.DTOs.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LectureController {

    private final ILectureService lectureService;

    @GetMapping("/schedule")
    public ResponseEntity<ScheduleDTO> getSchedule() {
        return ResponseEntity.ok(lectureService.getSchedule());
    }

}

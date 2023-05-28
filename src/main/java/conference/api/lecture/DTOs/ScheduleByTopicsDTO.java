package conference.api.lecture.DTOs;

import conference.api.lecture.Topic;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class ScheduleByTopicsDTO {
    Map<Topic, Set<LectureInfoDTO>> schedule = new HashMap<>();
}

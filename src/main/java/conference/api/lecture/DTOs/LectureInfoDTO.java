package conference.api.lecture.DTOs;

import conference.api.lecture.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureInfoDTO {
    long id;
    String name;
    String lecturer;
    Timestamp startDate;
    Timestamp endDate;
    Topic topic;
    int numberOfParticipants;
}

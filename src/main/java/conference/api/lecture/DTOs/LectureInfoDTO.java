package conference.api.lecture.DTOs;

import conference.api.lecture.Lecture;
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

    public LectureInfoDTO(Lecture lecture) {
        this.id = lecture.getId();
        this.name = lecture.getName();
        this.lecturer = lecture.getLecturer();
        this.startDate = lecture.getStartDate();
        this.endDate = lecture.getEndDate();
        this.topic = lecture.getTopic();
        this.numberOfParticipants = lecture.getParticipants().size();
    }
}

package conference.api.lecture;

import conference.Conference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LectureRepository {

    private final Conference conference;

    public List<Lecture> findAll() {
        return conference.getLectures();
    }

    public Lecture findById(long id) {
        return conference.getLectures().stream().filter(lecture -> lecture.getId() == id).findFirst().get();
    }

    public void addParticipantToLecture(long lectureId, long userId) {
        conference.getLectures().stream()
                .filter(lecture -> lecture.getId() == lectureId)
                .forEach(lecture -> lecture.getParticipants().add(userId));
    }

}

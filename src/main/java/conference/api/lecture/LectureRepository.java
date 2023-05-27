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

    public void removeParticipantFromLecture(long lectureId, long userId) {
        conference.getLectures().stream()
                .filter(lecture -> lecture.getId() == lectureId)
                .forEach(lecture -> lecture.getParticipants().remove(userId));
    }

    /**
     * @return For: <br>[1] 10:00 - 10:30 <br>[2] 10:30 - 11:00 <br>this function returns true
     */
    public boolean areDatesOverlappingInclusive(Lecture lecture1, Lecture lecture2) {
        return !lecture1.getStartDate().after(lecture2.getEndDate())
                && !lecture2.getStartDate().after(lecture1.getEndDate());
    }

    /**
     * @return For: <br>[1] 10:00 - 10:30 <br>[2] 10:30 - 11:00 <br>this function returns false
     */
    public boolean areDatesOverlappingExclusive(Lecture lecture1, Lecture lecture2) {
        return lecture1.getStartDate().before(lecture2.getEndDate())
                && lecture2.getStartDate().before(lecture1.getEndDate());
    }

}

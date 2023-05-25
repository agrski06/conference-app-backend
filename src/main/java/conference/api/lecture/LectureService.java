package conference.api.lecture;

import conference.Conference;
import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LectureService implements ILectureService {

    private final LectureRepository lectureRepository;

    @Override
    public ScheduleDTO getSchedule() {
        List<Lecture> lectures = lectureRepository.findAll();

        Set<LectureInfoDTO> firstBlock = new HashSet<>();
        Set<LectureInfoDTO> secondBlock = new HashSet<>();
        Set<LectureInfoDTO> thirdBlock = new HashSet<>();

        lectures.forEach(lecture -> {
            LectureInfoDTO lectureInfoDTO = new LectureInfoDTO(lecture.getName(),
                    lecture.getLecturer(),
                    lecture.getStartDate(),
                    lecture.getEndDate(),
                    lecture.getTopic(),
                    lecture.getParticipants().size());
            if (lecture.getStartDate().equals(Conference.firstLectureStartDate)) {
                firstBlock.add(lectureInfoDTO);
            }
            else if (lecture.getStartDate().equals(Conference.secondLectureStartDate)) {
                secondBlock.add(lectureInfoDTO);
            }
            else if (lecture.getStartDate().equals(Conference.thirdLectureStartDate)) {
                thirdBlock.add(lectureInfoDTO);
            }
        });

        return new ScheduleDTO(firstBlock, secondBlock, thirdBlock);
    }

}

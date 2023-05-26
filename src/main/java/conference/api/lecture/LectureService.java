package conference.api.lecture;

import conference.Conference;
import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.RegisterUserForLectureRequest;
import conference.api.lecture.DTOs.ScheduleDTO;
import conference.api.user.User;
import conference.api.user.UserRepository;
import conference.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService implements ILectureService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final UserService userService;

    @Override
    public ScheduleDTO getSchedule() {
        List<Lecture> lectures = lectureRepository.findAll();

        Set<LectureInfoDTO> firstBlock = new HashSet<>();
        Set<LectureInfoDTO> secondBlock = new HashSet<>();
        Set<LectureInfoDTO> thirdBlock = new HashSet<>();

        lectures.forEach(lecture -> {
            LectureInfoDTO lectureInfoDTO = new LectureInfoDTO(
                    lecture.getId(),
                    lecture.getName(),
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

    @Override
    public List<LectureInfoDTO> getLecturesForUser(String login) {
        Optional<User> user = userRepository.findByLogin(login);

        if (user.isEmpty())
            return null;

        List<Lecture> lectures = new ArrayList<>();
        user.get().getLectures().forEach(lectureId
                        -> lectures.add(lectureRepository.findById(lectureId)));

        return lectures.stream().map(LectureInfoDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean registerUserForLecture(RegisterUserForLectureRequest request) {
        User user = userService.registerUser(request.getLogin(), request.getEmail());
        Lecture lecture = lectureRepository.findById(request.getLectureId());

        // if user already registered to lecture
        if (user.getLectures().contains(request.getLectureId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already registered for this lecture");
        }

        // check capacity
        if (lecture.getParticipants().size() >= 5) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Capacity of 5 participants reached");
        }

        user.getLectures().add(lecture.getId());
        lecture.getParticipants().add(user.getId());
        userRepository.save(user);
        return true;
    }
}

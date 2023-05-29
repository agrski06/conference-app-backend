package conference.api.lecture;

import conference.Conference;
import conference.api.exceptions.NoSeatsLeftException;
import conference.api.exceptions.OverlappingLecturesException;
import conference.api.exceptions.UserAlreadyRegisteredForLectureException;
import conference.api.lecture.DTOs.LectureInfoDTO;
import conference.api.lecture.DTOs.RegisterUserForLectureRequest;
import conference.api.lecture.DTOs.ScheduleByTopicsDTO;
import conference.api.lecture.DTOs.ScheduleDTO;
import conference.api.user.DTOs.UserLecturesPreviewDTO;
import conference.api.user.User;
import conference.api.user.UserRepository;
import conference.api.user.UserService;
import conference.mail.IMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService implements ILectureService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final UserService userService;
    private final IMailSender mailSender;

    @Override
    public ScheduleDTO getSchedule() {
        List<Lecture> lectures = lectureRepository.findAll();

        Set<LectureInfoDTO> firstBlock = new HashSet<>();
        Set<LectureInfoDTO> secondBlock = new HashSet<>();
        Set<LectureInfoDTO> thirdBlock = new HashSet<>();

        lectures.forEach(lecture -> {
            LectureInfoDTO lectureInfoDTO = new LectureInfoDTO(lecture);
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
    public ScheduleByTopicsDTO getScheduleByTopics() {
        ScheduleByTopicsDTO result = new ScheduleByTopicsDTO();
        List<Lecture> lectures = lectureRepository.findAll();

        lectures.forEach(lecture -> {
            Set<LectureInfoDTO> lecturesDTOs;
            if (result.getSchedule().containsKey(lecture.getTopic())) {
                lecturesDTOs = result.getSchedule().get(lecture.getTopic());
                lecturesDTOs.add(new LectureInfoDTO(lecture));
            } else {
                lecturesDTOs = new HashSet<>(Set.of(new LectureInfoDTO(lecture)));
            }
            result.getSchedule().put(lecture.getTopic(), lecturesDTOs);
        });

        // sort the sets from result by date
        result.getSchedule().forEach((topic, lectureInfoDTOS) -> result.getSchedule().put(topic,
            lectureInfoDTOS.stream()
                    .sorted(Comparator.comparing(LectureInfoDTO::getStartDate))
                    .collect(Collectors.toCollection(LinkedHashSet::new))
        ));

        return result;
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
    public UserLecturesPreviewDTO registerUserForLecture(RegisterUserForLectureRequest request) {
        User user = userService.registerUser(request.getLogin(), request.getEmail());
        Lecture lecture = lectureRepository.findById(request.getLectureId());

        // if user already registered to lecture
        if (user.getLectures().contains(request.getLectureId())) {
            throw new UserAlreadyRegisteredForLectureException();
        }

        // check capacity
        if (lecture.getParticipants().size() >= 5) {
            throw new NoSeatsLeftException();
        }

        // check whether user is registered for another lecture at this time
        if (user.getLectures().stream()
                .map(lectureRepository::findById)
                .anyMatch(userLecture -> lectureRepository.areDatesOverlappingInclusive(userLecture, lecture))) {
            throw new OverlappingLecturesException();

        }

        user.getLectures().add(lecture.getId());
        user = userRepository.save(user);
        lectureRepository.addParticipantToLecture(lecture.getId(), user.getId());
        mailSender.sendRegisteredNotification(request.getEmail(), lecture);

        UserLecturesPreviewDTO result = new UserLecturesPreviewDTO();
        result.setEmail(user.getEmail());
        result.setLogin(user.getLogin());
        result.setLectures(user.getLectures().stream()
                .map(lectureRepository::findById)
                .map(LectureInfoDTO::new)
                .collect(Collectors.toList()));

        return result;
    }
}

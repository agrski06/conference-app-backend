package conference.api.stats;

import conference.api.lecture.Lecture;
import conference.api.lecture.LectureRepository;
import conference.api.lecture.Topic;
import conference.api.stats.DTOs.LectureStatisticsDTO;
import conference.api.stats.DTOs.TopicStatisticsDTO;
import conference.api.user.User;
import conference.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StatsService implements IStatsService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Override
    public TopicStatisticsDTO statsForTopic() {
        List<User> users = userRepository.findAll();

        // need to store IDs in set, because duplicates are not allowed in counting users interested in topics
        // for example: user can be registered for 2 lectures of the same topic, which would
        // produce invalid data (like 200% of interest in topic)
        Map<Topic, HashSet<Long>> usersInterestedInTopic = new HashMap<>();

        for (User user : users) {
            for (long lectureId : user.getLectures()) {
                Lecture lecture = lectureRepository.findById(lectureId);
                if (usersInterestedInTopic.containsKey(lecture.getTopic())) {
                    HashSet<Long> usersInterested = usersInterestedInTopic.get(lecture.getTopic());
                    usersInterested.add(user.getId());
                    usersInterestedInTopic.put(lecture.getTopic(), usersInterested);
                } else {
                    usersInterestedInTopic.put(lecture.getTopic(), new HashSet<>(Set.of(user.getId())));
                }
            }
        }

        TopicStatisticsDTO result = new TopicStatisticsDTO();
        int numberOfUsers = users.size();

        usersInterestedInTopic.forEach((topic, longs)
                -> result.getPercentageOfInterestForTopics()
                    .put(topic, (float) (longs.size() * 1.0 / numberOfUsers) * 100)
        );

        return result;
    }

    @Override
    public LectureStatisticsDTO statsForLecture() {
        int numberOfUsers = userRepository.findAll().size();
        List<Lecture> lectures = lectureRepository.findAll();
        Map<String, Float> usersRegisteredForLecture = new HashMap<>();

        lectures.forEach(lecture
                -> usersRegisteredForLecture
                .put(lecture.getName(),
                        numberOfUsers != 0 ? (float) (lecture.getParticipants().size() * 100.0 / numberOfUsers) : 0)
        );

        LectureStatisticsDTO stats = new LectureStatisticsDTO();
        stats.setPercentageOfInterestInLectures(usersRegisteredForLecture);
        return stats;
    }
}

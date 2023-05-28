package conference.api.stats.DTOs;

import conference.api.lecture.Topic;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TopicStatisticsDTO {
    private Map<Topic, Float> percentageOfInterestForTopics = new HashMap<>();
}

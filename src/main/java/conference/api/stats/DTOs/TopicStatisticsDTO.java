package conference.api.stats.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import conference.api.lecture.Topic;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TopicStatisticsDTO {
    @JsonProperty("Interest in topics")
    private Map<Topic, Float> percentageOfInterestForTopics = new HashMap<>();
}

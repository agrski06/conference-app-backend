package conference.api.stats.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class LectureStatisticsDTO {
    @JsonProperty("Interest in lectures")
    private Map<String, Float> percentageOfInterestInLectures;
}

package conference.api.stats.DTOs;

import lombok.Data;

import java.util.Map;

@Data
public class LectureStatisticsDTO {
    private Map<String, Float> percentageOfInterestInLectures;
}

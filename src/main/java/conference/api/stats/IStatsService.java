package conference.api.stats;

import conference.api.stats.DTOs.TopicStatisticsDTO;

public interface IStatsService {
    TopicStatisticsDTO statsForTopic();
}

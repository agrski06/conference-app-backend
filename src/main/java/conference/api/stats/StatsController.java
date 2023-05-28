package conference.api.stats;

import conference.api.stats.DTOs.LectureStatisticsDTO;
import conference.api.stats.DTOs.TopicStatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stats")
@RequiredArgsConstructor
public class StatsController {

    private final IStatsService statsService;

    @GetMapping("/topics")
    public ResponseEntity<TopicStatisticsDTO> getTopicStats() {
        return ResponseEntity.ok(statsService.statsForTopic());
    }

    @GetMapping("/lectures")
    public ResponseEntity<LectureStatisticsDTO> getLecturesStats() {
        return ResponseEntity.ok(statsService.statsForLecture());
    }
}

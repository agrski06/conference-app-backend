package conference.api.lecture.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleDTO {
    @JsonProperty("First block")
    Set<LectureInfoDTO> firstBlock;

    @JsonProperty("Second block")
    Set<LectureInfoDTO> secondBlock;

    @JsonProperty("Third block")
    Set<LectureInfoDTO> thirdBlock;
}

package conference.api.lecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lecture {

    private Long id;

    private String name;

    private String lecturer;

    private Timestamp startDate;

    private Timestamp endDate;

    private Topic topic;

    private Set<Integer> participants = new HashSet<>();

    public Lecture(Long id, String name, String lecturer, Timestamp startDate, Timestamp endDate, Topic topic) {
        this.id = id;
        this.name = name;
        this.lecturer = lecturer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.topic = topic;
    }
}

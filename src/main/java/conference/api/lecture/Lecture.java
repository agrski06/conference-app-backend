package conference.api.lecture;

import conference.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    String lecturer;

    Timestamp startDate;

    Timestamp endDate;

    @Enumerated(EnumType.STRING)
    Topic topic;

    @ManyToMany(mappedBy = "lectures")
    Set<User> participants;

}

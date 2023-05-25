package conference.api.lecture;

import conference.api.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    @Enumerated(EnumType.STRING)
    Topic topic;

    @ManyToMany(mappedBy = "lectures")
    Set<User> participants;

}

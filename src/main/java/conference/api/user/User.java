package conference.api.user;

import conference.api.lecture.Lecture;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "users") // USER is a keyword in H2
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String login;

    String email;

    @ManyToMany
    @JoinTable(name = "lectures_participants")
    Set<Lecture> lectures;

}

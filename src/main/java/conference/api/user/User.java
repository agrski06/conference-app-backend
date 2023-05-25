package conference.api.user;

import conference.api.lecture.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "users") // USER is a keyword in H2
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String login;

    String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lectures_participants")
    Set<Lecture> lectures;

}

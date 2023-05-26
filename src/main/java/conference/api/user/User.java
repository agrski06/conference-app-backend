package conference.api.user;

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

    private String login;

    private String email;

    @ElementCollection
    @CollectionTable(
            name = "NUMBER",
            joinColumns = @JoinColumn(name="LECTURE_ID")
    )
    private Set<Integer> lectures;

}

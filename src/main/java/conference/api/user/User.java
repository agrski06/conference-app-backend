package conference.api.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    // store IDs of Lectures in database
    @ElementCollection
    @CollectionTable(
            name = "PARTICIPANTS",
            joinColumns = @JoinColumn(name="USER_ID")
    )
    private List<Long> lectures;

}

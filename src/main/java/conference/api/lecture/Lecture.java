package conference.api.lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Lecture {
    @Id
    private Long id;

    String name;


}

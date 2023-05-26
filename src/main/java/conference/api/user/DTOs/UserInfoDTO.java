package conference.api.user.DTOs;

import conference.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDTO {
    String login;
    String email;
    int lecturesNumber;

    public UserInfoDTO(User user) {
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.lecturesNumber = user.getLectures().size();
    }
}

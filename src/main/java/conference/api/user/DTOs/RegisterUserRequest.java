package conference.api.user.DTOs;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String login;
    private String email;
}

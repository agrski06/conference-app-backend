package conference.api.user.DTOs;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String login;
    private String email;
}

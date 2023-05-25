package conference.api.user;

import conference.api.user.DTOs.UserInfoDTO;

public interface IUserService {
    UserInfoDTO registerUser(String login, String email);
}

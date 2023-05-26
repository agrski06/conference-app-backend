package conference.api.user;

import conference.api.user.DTOs.UserInfoDTO;

import java.util.Set;

public interface IUserService {

    /**
     * @param login - user login
     * @param email - user email
     * @return Returns new User object if user with given login has not been found in the database. Otherwise, returns
     * user from database
     */
    User registerUser(String login, String email);

    Set<UserInfoDTO> getAllUsers();
}

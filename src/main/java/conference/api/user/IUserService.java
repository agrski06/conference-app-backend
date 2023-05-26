package conference.api.user;

import conference.api.user.DTOs.RegisterUserRequest;
import conference.api.user.DTOs.UpdateUserRequest;
import conference.api.user.DTOs.UserInfoDTO;

import java.util.Set;

public interface IUserService {

    /**
     * This method is used for internal user registration (during registering for lecture)
     * @param login user login
     * @param email user email
     * @return Returns new User object if user with given login has not been found in the database. Otherwise, returns
     * user from database
     */
    User registerUser(String login, String email);

    /**
     * This method is used for user registration by endpoint
     * @param request contains login and email
     * @return UserInfoDTO - basic info about user
     */
    UserInfoDTO registerUser(RegisterUserRequest request);

    /**
     * Used for updating user
     * @param request contains login and email
     * @return UserInfoDTO - basic info about user
     */
    UserInfoDTO updateUser(UpdateUserRequest request);

    Set<UserInfoDTO> getAllUsers();
}

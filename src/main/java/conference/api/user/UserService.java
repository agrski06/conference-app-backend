package conference.api.user;

import conference.api.user.DTOs.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserInfoDTO registerUser(String login, String email) {
        Optional<User> user = userRepository.findByLogin(login);

        // login might be taken, check in call whether the emails match
        if (user.isPresent())
            return new UserInfoDTO(login, user.get().getEmail(), user.get().getLectures().size());

        User newUser = userRepository.save(new User(null, login, email, new HashSet<>()));
        return new UserInfoDTO(newUser.getLogin(), newUser.getEmail(), 0);
    }
}

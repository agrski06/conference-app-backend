package conference.api.user;

import conference.api.user.DTOs.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(String login, String email) {
        Optional<User> user = userRepository.findByLogin(login);

        // login might be taken, check in call whether the emails match
        return user.orElseGet(() -> new User(null, login, email, new ArrayList<>()));
    }

    @Override
    public Set<UserInfoDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserInfoDTO::new).collect(Collectors.toSet());
    }
}

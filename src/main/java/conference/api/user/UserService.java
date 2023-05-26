package conference.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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
}

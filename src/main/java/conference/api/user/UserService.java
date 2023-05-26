package conference.api.user;

import conference.api.user.DTOs.RegisterUserRequest;
import conference.api.user.DTOs.UpdateUserRequest;
import conference.api.user.DTOs.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<User> userInRepo = userRepository.findByLogin(login);

        if (userInRepo.isPresent()) {
            if (!userInRepo.get().getEmail().equals(email.toLowerCase())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Provided email doesn't match the login");
            }
        }

        return userInRepo.orElseGet(() -> new User(null, login, email.toLowerCase(), new ArrayList<>()));
    }

    @Override
    public UserInfoDTO registerUser(RegisterUserRequest request) {
        Optional<User> user = userRepository.findByLogin(request.getLogin());

        // check if email is the same as in database
        if (user.isPresent()) {
            if (user.get().getEmail().equals(request.getEmail().toLowerCase()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User already registered");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login already in use");
        }

        User savedUser = userRepository.save(
                new User(null, request.getLogin(), request.getEmail().toLowerCase(), new ArrayList<>()));

        return new UserInfoDTO(savedUser);

    }

    @Override
    public UserInfoDTO updateUser(UpdateUserRequest request) {
        Optional<User> user = userRepository.findByLogin(request.getLogin());

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given login not found");
        }

        User updatedUser = user.get();
        updatedUser.setEmail(request.getEmail().toLowerCase());
        updatedUser = userRepository.save(updatedUser);

        return new UserInfoDTO(updatedUser);
    }

    @Override
    public Set<UserInfoDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserInfoDTO::new).collect(Collectors.toSet());
    }
}

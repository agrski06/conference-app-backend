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
        Optional<User> userFoundByLogin = userRepository.findByLogin(login);
        Optional<User> userFoundByEmail = userRepository.findByEmail(email.toLowerCase());

        // this looks... it works
        if (userFoundByLogin.isPresent()) {
            if (!userFoundByLogin.get().getEmail().equals(email.toLowerCase())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Provided email doesn't match the login");
            }
            if (userFoundByEmail.isPresent()) {
                if (!userFoundByLogin.get().getEmail().equals(userFoundByEmail.get().getEmail())) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
                }
            }
        }

        return userFoundByLogin.orElseGet(() -> new User(null, login, email.toLowerCase(), new ArrayList<>()));
    }

    @Override
    public UserInfoDTO registerUser(RegisterUserRequest request) {
        Optional<User> userFoundByLogin = userRepository.findByLogin(request.getLogin());
        Optional<User> userFoundByEmail = userRepository.findByEmail(request.getEmail().toLowerCase());

        // check if email is the same as in database
        if (userFoundByLogin.isPresent()) {
            if (userFoundByLogin.get().getEmail().equals(request.getEmail().toLowerCase()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User already registered");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login already in use");
        }

        if (userFoundByEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        User savedUser = userRepository.save(
                new User(null, request.getLogin(), request.getEmail().toLowerCase(), new ArrayList<>()));

        return new UserInfoDTO(savedUser);
    }

    @Override
    public UserInfoDTO updateUser(UpdateUserRequest request) {
        Optional<User> userFoundByLogin = userRepository.findByLogin(request.getLogin());
        Optional<User> userFoundByEmail = userRepository.findByEmail(request.getEmail().toLowerCase());

        if (userFoundByLogin.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given login not found");
        }

        if (userFoundByEmail.isPresent()) {
            if (!userFoundByEmail.get().getLogin().equals(userFoundByLogin.get().getLogin()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        User updatedUser = userFoundByLogin.get();
        updatedUser.setEmail(request.getEmail().toLowerCase());
        updatedUser = userRepository.save(updatedUser);

        return new UserInfoDTO(updatedUser);
    }

    @Override
    public Set<UserInfoDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserInfoDTO::new).collect(Collectors.toSet());
    }
}

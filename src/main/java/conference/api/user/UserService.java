package conference.api.user;

import conference.api.lecture.Lecture;
import conference.api.lecture.LectureRepository;
import conference.api.user.DTOs.RegisterUserRequest;
import conference.api.user.DTOs.UpdateUserRequest;
import conference.api.user.DTOs.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Override
    public User registerUser(String login, String email) {
        Optional<User> userFoundByLogin = userRepository.findByLogin(login);
        Optional<User> userFoundByEmail = userRepository.findByEmail(email.toLowerCase());

        if (userFoundByLogin.isPresent()) {
            if (!userFoundByLogin.get().getEmail().equals(email.toLowerCase())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Provided email doesn't match the login");
            }
        }

        if (userFoundByEmail.isPresent() && userFoundByLogin.isPresent()) {
            if (!userFoundByLogin.get().getLogin().equals(userFoundByEmail.get().getLogin()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        if (userFoundByEmail.isPresent() && userFoundByLogin.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
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
    public UserInfoDTO cancelReservationForLecture(long lectureId, String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Lecture lecture = lectureRepository.findById(lectureId);

        if (user.get().getLectures().stream().noneMatch(userLecture -> Objects.equals(userLecture, lecture.getId()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not registered for this lecture");
        }

        user.get().getLectures().remove(lectureId);
        User savedUser = userRepository.save(user.get());
        lectureRepository.removeParticipantFromLecture(lectureId, user.get().getId());

        return new UserInfoDTO(savedUser);
    }

    @Override
    public Set<UserInfoDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserInfoDTO::new).collect(Collectors.toSet());
    }
}

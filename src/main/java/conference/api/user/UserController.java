package conference.api.user;

import conference.api.user.DTOs.RegisterUserRequest;
import conference.api.user.DTOs.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Set<UserInfoDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<UserInfoDTO> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }
}

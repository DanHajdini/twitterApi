package hajdini.daniel.twitter.api.controllers.security;

import hajdini.daniel.twitter.api.models.security.dtos.UserSessionDto;
import hajdini.daniel.twitter.api.models.security.dtos.UserTokenDto;
import hajdini.daniel.twitter.api.models.security.form.LoginForm;
import hajdini.daniel.twitter.api.models.security.form.RegisterForm;
import hajdini.daniel.twitter.bll.services.security.AuthService;
import hajdini.daniel.twitter.dl.entities.User;
import hajdini.daniel.twitter.il.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterForm form
    ) {
        authService.register(form.toUser());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(
            @Valid @RequestBody LoginForm form
    ) {
        User user = authService.login(form.login(), form.password());
        UserSessionDto userDTO = UserSessionDto.fromUser(user);
        String token = jwtUtil.generateToken(user);
        UserTokenDto userTokenDTO = new UserTokenDto(userDTO, token);
        return ResponseEntity.ok(userTokenDTO);
    }

}

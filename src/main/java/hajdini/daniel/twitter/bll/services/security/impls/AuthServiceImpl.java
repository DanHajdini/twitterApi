package hajdini.daniel.twitter.bll.services.security.impls;

import hajdini.daniel.twitter.bll.services.security.AuthService;
import hajdini.daniel.twitter.dal.repositories.UserRepository;
import hajdini.daniel.twitter.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User login(String login, String password) {
        User user = userRepository.findByUsernameOrEmail(login).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }

    @Override
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(login).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }
}

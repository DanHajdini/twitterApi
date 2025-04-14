package hajdini.daniel.twitter.bll.services.security;

import hajdini.daniel.twitter.dl.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    User login(String login, String password);
    void register(User user);
}

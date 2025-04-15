package hajdini.daniel.twitter.dal.initializers;

import hajdini.daniel.twitter.dal.repositories.PostRepository;
import hajdini.daniel.twitter.dal.repositories.UserRepository;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;
import hajdini.daniel.twitter.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {

            String password = passwordEncoder.encode("1234");

            List<User> users = List.of(
                    new User(
                            "admin",
                            "admin@email.be",
                            password,
                            "ad laurent",
                            null,
                            null,
                            null,
                            UserRole.ADMIN),
                    new User(
                            "user1",
                            "user@email.be",
                            password,
                            "master yi",
                            null,
                            null,
                            null,
                            UserRole.USER),
                    new User(
                            "user2",
                            "user2@email.be",
                            password,
                            "SNK fan page",
                            null,
                            null,
                            null,
                            UserRole.USER),
                    new User(
                            "danj",
                            "danj@email.com",
                            password,
                            "danj OSLM",
                            "luik",
                            "chie avant que la vie ne te chie dessus",
                            null,
                            UserRole.USER
                    )
            );
            userRepository.saveAll(users);
        }

        if (postRepository.count() == 0) {

            List<Post> posts = List.of(
                    new Post("test1", userRepository.findByUsername("danj")),
                    new Post("test2", userRepository.findByUsername("danj")),
                    new Post("test3", userRepository.findByUsername("danj")),
                    new Post("test4", userRepository.findByUsername("danj"))
            );
            postRepository.saveAll(posts);
        }
    }
}

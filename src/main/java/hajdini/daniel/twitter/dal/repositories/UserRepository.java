package hajdini.daniel.twitter.dal.repositories;

import hajdini.daniel.twitter.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.username ILIKE :login or u.email ILIKE :login")
    Optional<User> findByUsernameOrEmail(String login);
}

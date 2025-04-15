package hajdini.daniel.twitter.dal.repositories;

import hajdini.daniel.twitter.dl.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

}

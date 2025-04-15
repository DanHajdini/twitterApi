package hajdini.daniel.twitter.dal.repositories;

import hajdini.daniel.twitter.dl.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {


}

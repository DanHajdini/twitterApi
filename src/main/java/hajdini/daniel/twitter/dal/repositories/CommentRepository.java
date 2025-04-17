package hajdini.daniel.twitter.dal.repositories;

import hajdini.daniel.twitter.dl.entities.Comment;
import hajdini.daniel.twitter.dl.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findCommentsByPost(Post post);
    List<Comment> findCommentsByParent(Comment comment);
}

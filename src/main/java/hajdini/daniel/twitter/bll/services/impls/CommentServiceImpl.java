package hajdini.daniel.twitter.bll.services.impls;

import hajdini.daniel.twitter.bll.services.CommentService;
import hajdini.daniel.twitter.dal.repositories.CommentRepository;
import hajdini.daniel.twitter.dal.repositories.UserRepository;
import hajdini.daniel.twitter.dl.entities.Comment;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findCommentsByPost(post);
    }

    @Override
    public List<Comment> getCommentsByComment(Comment comment) {
        return commentRepository.findCommentsByParent(comment);
    }

    @Override
    public Comment getCommentById(String id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment with id " + id + " not found")
        );
    }

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment like(Comment comment, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found")
        );
        boolean liked = comment.getCommentLikes().stream().anyMatch(u -> u.getId().equals(existingUser.getId()));
        if (liked) {
            comment.removeCommentLike(existingUser);
        } else {
            comment.addCommentLike(existingUser);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment repost(Comment comment, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new RuntimeException("User with id " + user.getId() + " not found")
        );
        boolean reposted = comment.getCommentReposts().stream().anyMatch(u -> u.getId().equals(existingUser.getId()));
        if (reposted) {
            comment.removeCommentRepost(existingUser);
        } else {
            comment.addCommentRepost(existingUser);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Long getCommentsCountByPost(Post post) {
        return (long) commentRepository.findCommentsByPost(post).size();
    }

    @Override
    public Long getCommentsCountByComment(Comment comment) {
        return (long) commentRepository.findCommentsByParent(comment).size();
    }

}

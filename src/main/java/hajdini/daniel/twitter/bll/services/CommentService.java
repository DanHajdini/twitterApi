package hajdini.daniel.twitter.bll.services;

import hajdini.daniel.twitter.dl.entities.Comment;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPost(Post post);
    List<Comment> getCommentsByComment(Comment comment);
    Comment getCommentById(String id);
    void createComment(Comment comment);
    void deleteComment(Comment comment);
    Comment like(Comment comment, User user);
    Comment repost(Comment comment, User user);
    Long getCommentsCountByPost(Post post);
    Long getCommentsCountByComment(Comment comment);
}

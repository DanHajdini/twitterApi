package hajdini.daniel.twitter.api.models.comment.forms;

import hajdini.daniel.twitter.dl.entities.Comment;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;

public record CommentForm(
        String content
) {
    public Comment toComment(User user, Post post) {
        return new Comment(content, user, post);
    }

    public Comment toSubComment(User user, Comment parent) {
        return new Comment(content, user, parent);
    }
}

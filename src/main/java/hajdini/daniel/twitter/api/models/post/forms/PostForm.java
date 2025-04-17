package hajdini.daniel.twitter.api.models.post.forms;

import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;

public record PostForm(
        String content
) {
    public Post toPost(User user) {
        return new Post(content, user);
    }

    public Post toPostWithQuote(User user, Post post) {
        return new Post(content, user, post);
    }
}

package hajdini.daniel.twitter.bll.services;

import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Page<Post> findAllPosts(Pageable pageable);
    Post findOnePost(String id);
    void createPost(Post post);
    void deletePost(Post post);
    void like(Post post, User user);
    void unlike(Post post, User user);
    void repost(Post post, User user);
    void undoRepost(Post post, User user);

}

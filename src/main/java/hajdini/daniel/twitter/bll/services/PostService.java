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
    Post like(Post post, User user);
    Post repost(Post post, User user);
}

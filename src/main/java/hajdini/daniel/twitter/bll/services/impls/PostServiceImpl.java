package hajdini.daniel.twitter.bll.services.impls;

import hajdini.daniel.twitter.bll.services.PostService;
import hajdini.daniel.twitter.dal.repositories.PostRepository;
import hajdini.daniel.twitter.dal.repositories.UserRepository;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post findOnePost(String id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id" + id + "not found")
        );
    }

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void like(Post post, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        post.addPostLike(existingUser);
        postRepository.save(post);
    }

    @Override
    public void unlike(Post post, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        post.removePostLike(existingUser);
        postRepository.save(post);
    }

    @Override
    public void repost(Post post, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        post.addPostRepost(existingUser);
        postRepository.save(post);
    }

    @Override
    public void undoRepost(Post post, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        post.removePostRepost(existingUser);
        postRepository.save(post);
    }
}

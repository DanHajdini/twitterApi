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
    public Post like(Post post, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        boolean liked = post.getPostLikes().stream().anyMatch(u -> u.getId().equals(existingUser.getId()));
        if (liked) {
            post.removePostLike(existingUser);
        } else {
            post.addPostLike(existingUser);
        }

        postRepository.save(post);
        return post;
    }

    @Override
    public Post repost(Post post, User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        boolean reposted = post.getPostReposts().stream().anyMatch(u -> u.getId().equals(existingUser.getId()));
        if (reposted) {
            post.removePostRepost(existingUser);
        } else {
            post.addPostRepost(existingUser);
        }
        postRepository.save(post);
        return post;
    }



}

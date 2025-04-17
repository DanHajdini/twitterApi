package hajdini.daniel.twitter.api.controllers;

import hajdini.daniel.twitter.api.models.CustomPage;
import hajdini.daniel.twitter.api.models.post.dtos.PostDto;
import hajdini.daniel.twitter.api.models.post.forms.PostForm;
import hajdini.daniel.twitter.bll.services.PostService;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<CustomPage<PostDto>> getAllPosts(
            @RequestParam (required = false,defaultValue = "1") int page,
            @RequestParam (required = false,defaultValue = "10") int size,
            @RequestParam(required = false,defaultValue = "postedAt") String sort
    ) {
        Page<Post> posts = postService.findAllPosts(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, sort)));
        List<PostDto> dtos = posts.getContent().stream()
                .map(PostDto::fromPost)
                .toList();
        CustomPage<PostDto> results = new CustomPage<>(
                posts.getTotalPages(),
                posts.getNumber() + 1,
                (int) posts.getTotalElements(),
                posts.getSize(),
                dtos
        );
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        PostDto post = PostDto.fromPost(postService.findOnePost(id));
        return ResponseEntity.ok(post);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> post(
            @RequestBody PostForm form,
            @AuthenticationPrincipal User user
    ) {
        postService.createPost(form.toPost(user));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(postService.findOnePost(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDto> likePost(
            @PathVariable String id,
            @AuthenticationPrincipal User user
    ) {
        Post post = postService.findOnePost(id);
        PostDto updatedPost = PostDto.fromPost(postService.like(post, user));
        return ResponseEntity.ok(updatedPost);
    }

    @PutMapping("/{id}/repost")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDto> repostPost(
            @PathVariable String id,
            @AuthenticationPrincipal User user
    ) {
        Post post = postService.findOnePost(id);
        PostDto updatedPost = PostDto.fromPost(postService.repost(post, user));
        return ResponseEntity.ok(updatedPost);
    }

    @PostMapping("/{id}/quote")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> quotePost(
            @PathVariable String id,
            @RequestBody PostForm form,
            @AuthenticationPrincipal User user
    ) {
        Post post = postService.findOnePost(id);
        postService.createPost(form.toPostWithQuote(user, post));
        return ResponseEntity.noContent().build();
    }
}

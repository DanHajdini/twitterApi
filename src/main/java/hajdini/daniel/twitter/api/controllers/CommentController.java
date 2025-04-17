package hajdini.daniel.twitter.api.controllers;

import hajdini.daniel.twitter.api.models.comment.dtos.CommentDto;
import hajdini.daniel.twitter.api.models.comment.forms.CommentForm;
import hajdini.daniel.twitter.bll.services.CommentService;
import hajdini.daniel.twitter.bll.services.PostService;
import hajdini.daniel.twitter.dl.entities.Comment;
import hajdini.daniel.twitter.dl.entities.Post;
import hajdini.daniel.twitter.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsFromPost(
            @PathVariable String id
    ) {
        List<CommentDto> results = commentService.getCommentsByPost(postService.findOnePost(id))
                .stream()
                .map(CommentDto::fromComment)
                .toList();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsFromComment(
            @PathVariable String id
    ) {
        List<CommentDto> results = commentService.getCommentsByComment(commentService.getCommentById(id))
                .stream()
                .map(CommentDto::fromComment)
                .toList();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable String id
    ) {
        CommentDto comment = CommentDto.fromComment(commentService.getCommentById(id));
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/post/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> createComment(
            @PathVariable String id,
            @RequestBody CommentForm comment,
            @AuthenticationPrincipal User user
    ) {
        Post post = postService.findOnePost(id);
        commentService.createComment(comment.toComment(user, post));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/comment/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> createSubComment(
            @PathVariable String id,
            @RequestBody CommentForm comment,
            @AuthenticationPrincipal User user
    ) {
        Comment parent = commentService.getCommentById(id);
        commentService.createComment(comment.toSubComment(user, parent));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteComment(
            @PathVariable String id
    ) {
        commentService.deleteComment(commentService.getCommentById(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> likeComment(
            @PathVariable String id,
            @AuthenticationPrincipal User user
    ) {
        Comment comment = commentService.getCommentById(id);
        CommentDto updatedComment = CommentDto.fromComment(commentService.like(comment, user));
        return ResponseEntity.ok(updatedComment);
    }

    @PutMapping("/{id}/repost")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> repostComment(
            @PathVariable String id,
            @AuthenticationPrincipal User user
    ) {
        Comment comment = commentService.getCommentById(id);
        CommentDto updatedComment = CommentDto.fromComment(commentService.repost(comment, user));
        return ResponseEntity.ok(updatedComment);
    }
}

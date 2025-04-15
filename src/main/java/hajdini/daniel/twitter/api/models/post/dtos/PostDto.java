package hajdini.daniel.twitter.api.models.post.dtos;

import hajdini.daniel.twitter.dal.repositories.PostRepository;
import hajdini.daniel.twitter.dl.entities.Post;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public record PostDto(
        String alias,
        String username,
        String content,
        LocalDateTime postedAt,
        Long likes,
        Long reposts
) {
    public static PostDto fromPost(Post post) {
        return new PostDto(
                post.getAuthor().getAlias(),
                post.getAuthor().getUsername(),
                post.getContent(),
                post.getPostedAt(),
                (long) post.getPostLikes().size(),
                (long) post.getPostReposts().size()
        );
    }
}

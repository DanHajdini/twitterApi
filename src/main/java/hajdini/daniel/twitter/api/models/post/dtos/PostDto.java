package hajdini.daniel.twitter.api.models.post.dtos;

import hajdini.daniel.twitter.dl.entities.Post;

import java.time.LocalDateTime;

public record PostDto(
        String id,
        String alias,
        String username,
        String content,
        LocalDateTime postedAt,
        Long likes,
        Long reposts,
        QuoteDto quote
) {
    public static PostDto fromPost(Post post) {
        return new PostDto(
                post.getId(),
                post.getAuthor().getAlias(),
                post.getAuthor().getUsername(),
                post.getContent(),
                post.getPostedAt(),
                (long) post.getPostLikes().size(),
                (long) post.getPostReposts().size(),
                post.getQuotedPost() == null ? null :QuoteDto.fromPost(post.getQuotedPost())
        );
    }
}

package hajdini.daniel.twitter.api.models.post.dtos;

import hajdini.daniel.twitter.dl.entities.Post;

import java.time.LocalDateTime;

public record QuoteDto(
        String id,
        String alias,
        String username,
        String content,
        LocalDateTime postedAt
) {
    public static QuoteDto fromPost(Post post) {
        return new QuoteDto(
                post.getId(),
                post.getAuthor().getAlias(),
                post.getAuthor().getUsername(),
                post.getContent(),
                post.getPostedAt()
        );
    }
}

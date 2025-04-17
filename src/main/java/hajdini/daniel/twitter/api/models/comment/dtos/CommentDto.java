package hajdini.daniel.twitter.api.models.comment.dtos;

import hajdini.daniel.twitter.dl.entities.Comment;

import java.time.LocalDateTime;

public record CommentDto(
        String id,
        String alias,
        String username,
        String content,
        LocalDateTime postedAt,
        Long likes,
        Long reposts,
        Long commentsCount
) {
    public static CommentDto fromComment(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getAuthor().getAlias(),
                comment.getAuthor().getUsername(),
                comment.getContent(),
                comment.getCommentedAt(),
                (long) comment.getCommentLikes().size(),
                (long) comment.getCommentReposts().size(),
                2L //TODO : change value
        );
    }
}

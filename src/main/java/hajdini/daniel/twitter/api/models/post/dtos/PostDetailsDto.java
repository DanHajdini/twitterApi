package hajdini.daniel.twitter.api.models.post.dtos;

import hajdini.daniel.twitter.dl.entities.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailsDto(
        String id,
        String alias,
        String username,
        String content,
        LocalDateTime postedAt,
        Long likes,
        Long reposts,
        List<Comment> comments

) {

}

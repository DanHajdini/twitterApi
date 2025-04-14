package hajdini.daniel.twitter.dl.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class Post {

    @Id
    private String id;

    private String content;

    private LocalDateTime postedAt;

    @ManyToMany
    private List<User> postReposts;

    @ManyToMany
    private List<User> postLikes;

    public Post() {
        this.id = UUID.randomUUID().toString();
        this.postReposts = new ArrayList<>();
        this.postLikes = new ArrayList<>();
    }

    public Post(String content) {
        this();
        this.content = content;
        this.postedAt = LocalDateTime.now();
    }
}

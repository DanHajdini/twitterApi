package hajdini.daniel.twitter.dl.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class Post {

    @Id
    private String id;

    @Setter
    @Column(nullable = false)
    private String content;

    private LocalDateTime postedAt;

    @ManyToOne
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> postReposts;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> postLikes;

    public Post() {
        this.id = UUID.randomUUID().toString();
        this.postReposts = new ArrayList<>();
        this.postLikes = new ArrayList<>();
    }

    public Post(String content, User author) {
        this();
        this.content = content;
        this.author = author;
        this.postedAt = LocalDateTime.now();
    }

    public void addPostLike(User user) {
        this.postLikes.add(user);
    }

    public void addPostRepost(User user) {
        this.postReposts.add(user);
    }

    public void removePostLike(User user) {
        this.postLikes.remove(user);
    }

    public void removePostRepost(User user) {
        this.postReposts.remove(user);
    }
}

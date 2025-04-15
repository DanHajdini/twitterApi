package hajdini.daniel.twitter.dl.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @AllArgsConstructor
@EqualsAndHashCode @ToString
public class Comment {

    @Id
    private String id;

    @Setter
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime commentedAt;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> commentReposts;

    @ManyToMany(fetch = FetchType.EAGER)
    List<User> commentLikes;


    public Comment() {
        this.id = UUID.randomUUID().toString();
        this.commentReposts = new ArrayList<>();
        this.commentLikes = new ArrayList<>();
    }

    public Comment(String content) {
        this();
        this.content = content;
    }
}

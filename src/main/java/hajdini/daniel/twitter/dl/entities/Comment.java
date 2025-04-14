package hajdini.daniel.twitter.dl.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime commentedAt;

    @ManyToMany
    private List<User> commentReposts;

    @ManyToMany List<User> commentLikes;


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

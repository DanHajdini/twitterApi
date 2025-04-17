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

    @ManyToOne
    private Comment parent;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> commentReposts;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> commentLikes;


    public Comment() {
        this.id = UUID.randomUUID().toString();
        this.commentReposts = new ArrayList<>();
        this.commentLikes = new ArrayList<>();
        this.commentedAt = LocalDateTime.now();
    }

    public Comment(String content, User author, Post post) {
        this();
        this.content = content;
        this.author = author;
        this.post = post;
    }

    public Comment(String content,User author, Comment parent) {
        this();
        this.content = content;
        this.author = author;
        this.parent = parent;
    }

    public void addCommentLike(User u) {
        this.commentLikes.add(u);
    }

    public void addCommentRepost(User u) {
        this.commentReposts.add(u);
    }

    public void removeCommentLike(User u) {
        this.commentLikes.remove(u);
    }

    public void removeCommentRepost(User u) {
        this.commentReposts.remove(u);
    }
}

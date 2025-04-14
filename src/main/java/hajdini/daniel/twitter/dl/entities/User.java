package hajdini.daniel.twitter.dl.entities;

import hajdini.daniel.twitter.dl.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "user_")
@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class User implements UserDetails {

    @Id
    private String id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(length = 50)
    private String alias;

    @Column(length = 50)
    private String location;

    private String bio;

    private String profileImageUrl;

    @ManyToMany
    private List<User> followers;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.followers = new ArrayList<>();
    }

    public User(String username, String email, String password, String alias) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.alias = alias;
        this.role = UserRole.USER;
    }

    public User(String username, String email, String password, String alias, String location, String bio, String profileImageUrl, UserRole role) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.alias = alias;
        this.location = location;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

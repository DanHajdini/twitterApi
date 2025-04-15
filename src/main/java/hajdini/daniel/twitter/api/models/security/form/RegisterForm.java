package hajdini.daniel.twitter.api.models.security.form;

import hajdini.daniel.twitter.dl.entities.User;

public record RegisterForm(
        String username,
        String email,
        String password,
        String alias
) {
    public User toUser() {
       return new User(
               this.username,
               this.email,
               this.password,
               this.alias
       );
    }
}

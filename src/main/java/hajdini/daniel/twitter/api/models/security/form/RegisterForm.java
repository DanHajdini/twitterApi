package hajdini.daniel.twitter.api.models.security.form;

import hajdini.daniel.twitter.dl.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterForm(
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
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

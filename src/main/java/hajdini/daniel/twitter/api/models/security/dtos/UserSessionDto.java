package hajdini.daniel.twitter.api.models.security.dtos;

import hajdini.daniel.twitter.dl.entities.User;
import hajdini.daniel.twitter.dl.enums.UserRole;

public record UserSessionDto(
        String id,
        String username,
        UserRole role
) {
    public static UserSessionDto fromUser(User user) {
        return new UserSessionDto(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}

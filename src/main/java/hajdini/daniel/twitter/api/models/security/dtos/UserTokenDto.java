package hajdini.daniel.twitter.api.models.security.dtos;

public record UserTokenDto(
        UserSessionDto user,
        String token
) {
}

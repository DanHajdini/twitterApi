package hajdini.daniel.twitter.api.models.dtos;

public record UserTokenDto(
        UserSessionDto user,
        String token
) {
}

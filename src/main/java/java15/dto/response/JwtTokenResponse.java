package java15.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record JwtTokenResponse(
        String token,
        ZonedDateTime issueAt,
        ZonedDateTime expiresAt
) {
}

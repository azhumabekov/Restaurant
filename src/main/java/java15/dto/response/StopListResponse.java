package java15.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class StopListResponse {
    private Long id;
    private String reason;
    private Long menuItemId;
}
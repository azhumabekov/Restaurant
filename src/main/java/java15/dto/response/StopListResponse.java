package java15.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StopListResponse {
    private Long id;
    private String reason;
    private Long menuItemId;
}
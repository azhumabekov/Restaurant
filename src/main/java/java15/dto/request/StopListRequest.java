package java15.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopListRequest {
    private String reason;
    private Long menuItemId;
}
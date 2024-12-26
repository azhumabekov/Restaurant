package java15.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopListRequest {
    @NotNull(message = "reason cannot be null")
    private String reason;
    @NotNull
    private Long menuItemId;
}
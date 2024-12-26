package java15.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ChequeRequest {
    private Long employeeId;
    private List<Long> menuItemIds;
    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be positive")
    private String priceAverage;
}

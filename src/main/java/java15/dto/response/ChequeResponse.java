package java15.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class ChequeResponse {
    private Long id;
    private Long priceAverage;
    private LocalDate createdAt;
    private List<MenuItemResponse> menuItems;
}

package java15.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
public class ChequeResponse {
    private Long id;
    private Long priceAverage;
    private LocalDate createdAt;
    private List<MenuItemResponse> menuItems;
}

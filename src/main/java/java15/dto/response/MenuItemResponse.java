package java15.dto.response;

import java15.models.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private int price;
    private String description;
    private boolean isVegetarian;

    public MenuItemResponse(MenuItem menuItem) {
    }
}

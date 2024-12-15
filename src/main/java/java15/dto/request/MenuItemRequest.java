package java15.dto.request;

import java15.enums.RestType;
import lombok.Data;

@Data
public class MenuItemRequest {
    private String name;
    private String imageUrl;
    private int price;
    private String description;
    private boolean isVegetarian;
}

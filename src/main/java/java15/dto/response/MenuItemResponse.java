package java15.dto.response;

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


}

package java15.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class SubCategoryRequest {
    private Long categoryId;
    private String name;
    private Long menuItemId;
//    private List<MenuItemRequest> menuItems;
//    private CategoryRequest category;

}

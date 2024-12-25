package java15.dto.response;

import java15.dto.request.CategoryRequest;
import java15.dto.request.MenuItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubCategoryResponse {
    private Long id;
    private String name;
    private Long categoryId;
    private Long menuItemId;
//    private List<MenuItemRequest> menuItems;
//    private CategoryRequest category;
}

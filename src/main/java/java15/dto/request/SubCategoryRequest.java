package java15.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubCategoryRequest {
    private String name;
    private List<MenuItemRequest> menuItems;
    private CategoryRequest category;

}

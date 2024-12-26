package java15.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class SubCategoryRequest {
    @NotBlank(message = "category id wis null")
    private Long categoryId;
    @NotBlank(message = "name wis null")
    private String name;
    @NotBlank(message = "menu item id wis null")
    private Long menuItemId;
}

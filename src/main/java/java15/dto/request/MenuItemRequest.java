package java15.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MenuItemRequest {
    @NotBlank(message = "name wis null")
    @Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String name;
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "invalid image url")
    private String imageUrl;

    @Positive(message = "quantity must be greater than 0")
    @NotNull(message = "sub category id wis null")
    private Long restaurantId;
    @Min(value = 1, message = "price must be greater than 0")
    private int price;

    @NotBlank(message = "description wis null")
    @Size(max = 255)
    private String description;
    @NotNull(message = "isVegetarian wis null")
    private boolean isVegetarian;
}

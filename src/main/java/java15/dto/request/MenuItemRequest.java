package java15.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MenuItemRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$")
    private String imageUrl;

    @Min(1)
    private int price;

    @Size(max = 255)
    private String description;
    private boolean isVegetarian;
}

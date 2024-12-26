package java15.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java15.dto.request.MenuItemRequest;
import java15.dto.response.MenuItemResponse;
import java15.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "MenuItem")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @Secured({"ADMIN", "WAITER", "CHEF"})
    @GetMapping
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }


    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    @Secured({"WAITER", "ADMIN"})
    @PostMapping
    public ResponseEntity<MenuItemResponse> createMenuItem(@RequestBody @Valid MenuItemRequest menuItemRequest) {
        return ResponseEntity.ok(menuItemService.createMenuItem(menuItemRequest));
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}

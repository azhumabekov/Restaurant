package java15.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java15.dto.request.CategoryRequest;
import java15.dto.response.CategoryResponse;
import java15.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
@Tag(name = "category")
public class CategoryController {

    private final CategoryService categoryService;

    @Secured("ADMIN")
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @Secured("ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @Secured("ADMIN")
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.status(201).body(categoryService.createCategory(request));
    }
    @Secured("ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }
    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
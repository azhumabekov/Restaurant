package java15.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java15.dto.request.SubCategoryRequest;
import java15.dto.response.SubCategoryResponse;
import java15.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@Tag(name = "SubCategory", description = "Endpoints for managing subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    @Operation(summary = "Create a new subcategory")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "SubCategory created successfully"),
            @ApiResponse(responseCode = "404", description = "Category or MenuItem not found")
    })
    public ResponseEntity<SubCategoryResponse> createSubCategory(@RequestBody SubCategoryRequest request) {
        SubCategoryResponse response = subCategoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a subcategory by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SubCategory found"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<SubCategoryResponse> getSubCategoryById(@PathVariable Long id) {
        SubCategoryResponse response = subCategoryService.findById(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    @Operation(summary = "Get all subcategories")
    @ApiResponse(responseCode = "200", description = "List of SubCategories retrieved")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategories() {
        List<SubCategoryResponse> responses = subCategoryService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing subcategory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SubCategory updated successfully"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<String> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryRequest request) {
        subCategoryService.update(id, request);
        return ResponseEntity.ok("SubCategory updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subcategory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SubCategory deleted successfully"),
            @ApiResponse(responseCode = "404", description = "SubCategory not found")
    })
    public ResponseEntity<String> deleteSubCategory(@PathVariable Long id) {
        subCategoryService.delete(id);
        return ResponseEntity.ok("SubCategory deleted successfully");
    }
}
package java15.service.impl;

import java15.dto.request.CategoryRequest;
import java15.dto.response.CategoryResponse;
import java15.models.Category;
import java15.repository.CategoryRepository;
import java15.repository.SubCategoryRepository;
import java15.service.CategoryService;
import java15.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryRepo.save(category);
        return new CategoryResponse(category.getId(), category.getName());
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        return new CategoryResponse(category.getId(), category.getName());
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        subCategoryRepository.deleteByCategory(category);
        categoryRepo.deleteById(id);

    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
//        return categoryRepo.saveAndFlush(id, request);
        return null;
    }

}

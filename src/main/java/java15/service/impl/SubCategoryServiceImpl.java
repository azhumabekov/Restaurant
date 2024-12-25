package java15.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java15.dto.request.SubCategoryRequest;
import java15.dto.response.SubCategoryResponse;
import java15.models.Category;
import java15.models.SubCategory;
import java15.repository.CategoryRepository;
import java15.repository.MenuItemRepository;
import java15.repository.SubCategoryRepository;
import java15.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public SubCategoryResponse create(SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.getName());

        Category category = categoryRepository.findById(subCategoryRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        subCategory.setCategory(category);
        menuItemRepository.findById(subCategoryRequest.getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));
        subCategory.setCategory(category);


        subCategoryRepository.save(subCategory);
        return new SubCategoryResponse(
                subCategory.getId(),
                subCategory.getName(),
                subCategory.getMenuItem().getId(),
                subCategory.getCategory().getId()
        );
    }

    @Override
    public SubCategoryResponse findById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));

        return new SubCategoryResponse(
                subCategory.getId(),
                subCategory.getName(),
                subCategory.getCategory().getId(),
                subCategory.getMenuItem().getId()
        );
    }

    @Override
    public List<SubCategoryResponse> findAll() {
        return subCategoryRepository.findAll().stream()
                .map(subCategory -> new SubCategoryResponse(
                        subCategory.getId(),
                        subCategory.getName(),
                        subCategory.getMenuItem().getId(),
                        subCategory.getCategory().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));

        subCategory.setName(subCategoryRequest.getName());
//        subCategory.setMenuItem(subCategoryRequest.getMenuItems());
//        subCategory.setCategory(subCategoryRequest.getCategory());

        subCategoryRepository.save(subCategory);
    }

    @Override
    public void delete(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubCategory not found"));

        subCategoryRepository.delete(subCategory);
    }
}
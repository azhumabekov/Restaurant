package java15.service;

import java15.dto.request.SubCategoryRequest;
import java15.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponse create(SubCategoryRequest subCategoryRequest);

    SubCategoryResponse findById(Long id);

    List<SubCategoryResponse> findAll();

    void update(Long id, SubCategoryRequest subCategoryRequest);

    void delete(Long id);
}

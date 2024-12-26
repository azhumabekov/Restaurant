package java15.service;

import java15.dto.request.StopListRequest;
import java15.dto.response.StopListResponse;

import java.util.List;

public interface StopListService {
    StopListResponse create(StopListRequest stopListRequest);

    StopListResponse findById(Long id);

    List<StopListResponse> findAll();

    StopListResponse update(Long id, StopListRequest stopListRequest);

    void delete(Long id);


}

package java15.service;

import java15.dto.request.ChequeRequest;
import java15.dto.response.ChequeResponse;

import java.util.List;

public interface ChequeService {
    List<ChequeResponse> getAllCheques();

    ChequeResponse getChequeById(Long id);

    ChequeResponse createCheque(ChequeRequest request);

    ChequeResponse updateCheque(Long id, ChequeRequest request);

    void deleteCheque(Long id);

    String priceAverage();
}

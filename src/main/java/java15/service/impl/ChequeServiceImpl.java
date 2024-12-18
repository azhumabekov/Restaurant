package java15.service.impl;

import java15.dto.request.ChequeRequest;
import java15.dto.response.ChequeResponse;
import java15.service.ChequeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeServiceImpl implements ChequeService {

    @Override
    public List<ChequeResponse> getAllCheques() {
        return List.of();
    }

    @Override
    public ChequeResponse getChequeById(Long id) {
        return null;
    }

    @Override
    public ChequeResponse createCheque(ChequeRequest request) {
        return null;
    }

    @Override
    public ChequeResponse updateCheque(Long id, ChequeRequest request) {
        return null;
    }

    @Override
    public void deleteCheque(Long id) {

    }
}

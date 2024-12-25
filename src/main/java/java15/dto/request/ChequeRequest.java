package java15.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ChequeRequest {
    private Long employeeId;
    private List<Long> meetingIds;
    private String priceAverage;
}

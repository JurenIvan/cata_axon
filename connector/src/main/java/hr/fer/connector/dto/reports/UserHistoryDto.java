package hr.fer.connector.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryDto {

    private long userId;
    private List<String> pending;
    private List<String> accepted;
    private List<String> pendinqQuit;
    private List<String> acceptedQuit;
    private List<String> refused;
}

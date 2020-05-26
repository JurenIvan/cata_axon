package hr.fer.connector.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearPassengerCount {

    private int year;
    private int passengerCount;
}

package hr.fer.connector.dto.trips;

import hr.fer.connector.model.ContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelTripDto extends ContextHolder {

    private String explanation;
}

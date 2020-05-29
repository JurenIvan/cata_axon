package hr.fer.connector.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTrip {

    private long userId;
    private String tripId;
}

package hr.fer.connector.dto.trips;

import hr.fer.connector.model.ContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto extends ContextHolder {

    private String tripId;
    private String title;
    private String description;
    private double price;
    private LocalDateTime date;
    private LocalDateTime cancellationDate;
    private int passengersCount;
}

package hr.fer.connector.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripPreviewDto {

    private String tripId;
    private String title;
    private String description;
    private double price;
    private LocalDateTime date;
    private LocalDateTime cancellationDate;
    private int passengersCount;
}

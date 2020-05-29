package hr.fer.cata.trips.projections.availabletrips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOverview {

    @Id
    private String tripId;
    private String title;
    private String description;
    private double price;
    private LocalDateTime date;
    private LocalDateTime cancellationDate;
    private int passengersCount = 0;
}

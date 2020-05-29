package hr.fer.connector.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDetailsDto {

    private String tripId;
    private String title;
    private String description;
    private double price;
    private LocalDateTime date;
    private LocalDateTime cancellationDate;

    private List<Long> pendingUsersIds;
    private List<Long> quittersWhilePendingUsersIds;
    private List<Long> approvedUsersIds;
    private List<Long> refusedUsersIds;
    private List<Long> quittersUsersIds;
}

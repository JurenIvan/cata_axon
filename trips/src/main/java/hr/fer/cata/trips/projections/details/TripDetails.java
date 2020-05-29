package hr.fer.cata.trips.projections.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDetails {

    @Id
    private String tripId;

    @ElementCollection
    private List<Long> pendingUsersIds;
    @ElementCollection
    private List<Long> quittersWhilePendingUsersIds;
    @ElementCollection
    private List<Long> approvedUsersIds;
    @ElementCollection
    private List<Long> refusedUsersIds;
    @ElementCollection
    private List<Long> quittersUsersIds;


    public TripDetails(String tripId) {
        this.tripId = tripId;
        approvedUsersIds = new ArrayList<>();
        pendingUsersIds = new ArrayList<>();
        refusedUsersIds = new ArrayList<>();
        quittersUsersIds = new ArrayList<>();
        quittersWhilePendingUsersIds = new ArrayList<>();
    }
}

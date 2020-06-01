package hr.fer.cata.reports.projection.userhistory;

import hr.fer.connector.dto.reports.UserHistoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory {

    @Id
    private long userId;
    @ElementCollection
    private List<String> pending;
    @ElementCollection
    private List<String> accepted;
    @ElementCollection
    private List<String> pendinqQuit;
    @ElementCollection
    private List<String> acceptedQuit;
    @ElementCollection
    private List<String> refused;

    public UserHistory(long userId) {
        this.userId = userId;
        pending = new ArrayList<>();
        accepted = new ArrayList<>();
        pendinqQuit = new ArrayList<>();
        acceptedQuit = new ArrayList<>();
        refused = new ArrayList<>();
    }

    public UserHistoryDto toDto() {
        return new UserHistoryDto(userId, pending, accepted, pendinqQuit, acceptedQuit, refused);
    }
}

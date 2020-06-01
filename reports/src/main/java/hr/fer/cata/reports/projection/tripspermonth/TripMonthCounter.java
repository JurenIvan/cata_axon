package hr.fer.cata.reports.projection.tripspermonth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.nCopies;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TripMonthCounter {

    @Id
    private int year;
    @ElementCollection
    private List<Integer> passengerCountPerMonth;

    public TripMonthCounter(int year) {
        this.year = year;
        passengerCountPerMonth = new ArrayList<>(nCopies(12, 0));
    }
}

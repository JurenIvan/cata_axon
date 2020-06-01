package hr.fer.cata.reports.projection.passengersperyear;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PassengerPerYear {

    @Id
    private Integer year;
    private long passengerCount;

}

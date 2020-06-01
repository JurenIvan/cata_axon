package hr.fer.cata.email.projections.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String receiver;
    private String content;
    private Instant time;
}

package hr.fer.cata.email.projections.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDetails {

    @Id
    private Long id;
    private String receiver;
    private String content;
    private String senderApplication;
}

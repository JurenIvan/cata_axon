package hr.fer.connector.dto.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String receiver;
    private String content;
    private Instant time;
}

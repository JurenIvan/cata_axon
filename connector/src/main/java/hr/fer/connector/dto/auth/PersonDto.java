package hr.fer.connector.dto.auth;

import hr.fer.connector.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PersonDto {

    private Long id;
    private String email;
    private String nickname;
    private Role role;

}

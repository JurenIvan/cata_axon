package hr.fer.connector.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ContextHolder {

    private Long id;
    private String email;
    private String nickname;
    private Role role;
    private String jwtToken;

}

package hr.fer.connector.dto.auth;


import hr.fer.connector.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponseDto {

    private String token;
    private Long userId;
    private Role role;
}

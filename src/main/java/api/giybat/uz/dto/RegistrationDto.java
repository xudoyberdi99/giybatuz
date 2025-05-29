package api.giybat.uz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationDto {
    @NotBlank(message = "not empty name")
    private String name;

    @NotBlank(message = "not empty username")
    private String username;

    @NotBlank(message = "not empty password")
    private String password;
}

package exercise.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
// BEGIN
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestCreateDTO {
    @NotBlank
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @Pattern(regexp = "\\+\\d{11,13}", message = "Номер телефона phoneNumber должен начинаться с символа + и содержать от 11 до 13 цифр")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}", message = "Номер клубной карты clubCard должен состоять ровно из четырех цифр")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
// END

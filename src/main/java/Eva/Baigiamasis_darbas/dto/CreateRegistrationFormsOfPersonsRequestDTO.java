package Eva.Baigiamasis_darbas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class CreateRegistrationFormsOfPersonsRequestDTO {

    private String name;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    @Override
    public String toString() {
        return "CreateRegistrationFormsOfPersonsRequestDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}

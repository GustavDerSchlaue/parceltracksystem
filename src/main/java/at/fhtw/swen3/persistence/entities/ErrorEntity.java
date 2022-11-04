package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "Errormessage cannot be null")
    @NotBlank(message = "Errormessage cannot be blank")
    @Size(min = 2, max = 100000, message = "A valid errormessage must contain more than 2 characters and max 100000")
    private String errorMessage;
}

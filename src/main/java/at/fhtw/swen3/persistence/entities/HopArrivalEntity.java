package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="hopArrival")
public class HopArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "Code cannot be null")
    @NotBlank(message = "Code cannot be blank")
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$", message = "A valid code must not contain special characters and must have minimum 5 digits for example: 'AZZZ9992'")
    @Column
    private String code;
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 2, max = 1000000, message = "A valid description must contain more than 2 characters and max 1000000")
    @Column
    private String description;
    @NotNull(message = "Datetime cannot be null")
    @FutureOrPresent(message =  "A valid dateTime must present or in the future")
    @Column
    private OffsetDateTime dateTime;
}

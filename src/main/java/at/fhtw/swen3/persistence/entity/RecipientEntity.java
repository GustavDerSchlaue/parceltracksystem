package at.fhtw.swen3.persistence.entity;

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
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "A valid name must contain more than 2 characters and max 30")
    private String name;
    @NotNull(message = "Street cannot be null")
    @NotBlank(message = "Street cannot be blank")
    @Size(min = 4, max = 50, message = "A valid street must contain more than 4 characters and max 50")
    private String street;
    @NotNull(message = "Postalcode can not be null")
    @NotBlank(message = "Postalcode cannot be blank")
    @Size(min = 4, max = 10, message = "A valid Postalcode must contain more than 4 characters and max 10")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "must not contain special characters")
    private String postalCode;
    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be blank")
    @Size(min = 4, max = 30, message = "A valid City must contain more than 4 characters and max 30")
    private String city;
    @NotNull(message = "Country cannot be null")
    @NotBlank(message = "Country cannot be blank")
    @Size(min = 4, max = 30, message = "A valid Country must contain more than 4 characters and max 30")
    private String country;
}